/**
 * 
 */
package com.xzg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cmd.GetDeploymentProcessDiagramCmd;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {
	@Resource
	ProcessEngine engine;
	/**
	 * �г���������ģ��
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(ModelAndView mav) {
		 //mav.addObject("list", Util.list());
		// //����ModelAndView���캯������ָ������ҳ������ƣ�Ҳ����ͨ��setViewName��������������Ҫ��ת��ҳ�棻 
		mav.setViewName("activiti/template");
		return mav;
	}
	/**
	 * ��������startWorkflow.do
	 */
	@RequestMapping("startWorkflow.do")
	public ModelAndView deploy(String processName, ModelAndView mav) {
		RepositoryService service = engine.getRepositoryService();
		if (null != processName)
			service.createDeployment()
			.addClasspathResource("com/lin/workflow/leaveWork.bpmn").deploy();
		List<ProcessDefinition> list = service.createProcessDefinitionQuery()
				.list();
		mav.addObject("list", list);//���������
		mav.setViewName("activiti/deployed");
		return mav;
	}

	/**
	 * �Ѳ��������б�
	 */
	@RequestMapping("deployed")
	public ModelAndView deployed(ModelAndView mav) {
		RepositoryService service = engine.getRepositoryService();
		List<ProcessDefinition> list = service.createProcessDefinitionQuery()
		.list();
		mav.addObject("list", list);
		mav.setViewName("activiti/deployed");
		return mav;
	}

	/**
	 * 
	 * ����һ������ʵ��
	 */
	@RequestMapping("start")
	public ModelAndView start(String id, ModelAndView mav) {
		RuntimeService service = engine.getRuntimeService();
		service.startProcessInstanceById(id);
		List<ProcessInstance> list = service.createProcessInstanceQuery()
		.list();
		mav.addObject("list", list);
		mav.setViewName("activiti/started");
		return mav;

	}
	/**
	 * 
	 * ��������������ʵ��
	 */
	@RequestMapping("started")
	public ModelAndView started(ModelAndView mav) {
		RuntimeService service = engine.getRuntimeService();
		List<ProcessInstance> list = service.createProcessInstanceQuery()
		.list();
		mav.addObject("list", list);
		mav.setViewName("activiti/started");
		return mav;
	}
	/**
	 * 
	 * ���������б�
	 */
	@RequestMapping("task")
	public ModelAndView task(ModelAndView mav) {
		TaskService service = engine.getTaskService();
		List<Task> list = service.createTaskQuery().list();
		mav.addObject("list", list);
		mav.setViewName("activiti/task");
		return mav;
	}
	/**
	 * 
	 * ��ɵ�ǰ�ڵ�
	 */
	@RequestMapping("complete")
	public ModelAndView complete(ModelAndView mav, String id) {
		TaskService service = engine.getTaskService();
		service.complete(id);
		return new ModelAndView("redirect:task");
	}
	/**
	 * 
	 * ��������������ʵ��
	 * @throws IOException
	 */
	@RequestMapping("graphics")
	public void graphics(String definitionId, String instanceId,

	String taskId, ModelAndView mav, HttpServletResponse response)
	throws IOException {
		response.setContentType("image/png");
		Command<InputStream> cmd = null;
		if (definitionId != null) {
			cmd = new GetDeploymentProcessDiagramCmd(definitionId);
		}
		  /*if (instanceId != null) {
		  cmd = new ProcessInstanceDiagramCmd(instanceId);
		  }*/
		if (taskId != null) {
			Task task = engine.getTaskService().createTaskQuery()
					.taskId(taskId).singleResult();
			  /*cmd = new ProcessInstanceDiagramCmd(
			  task.getProcessInstanceId());
			  }*/
			if (cmd != null) {
				InputStream is = engine.getManagementService().executeCommand(
						cmd);
				int len = 0;
				byte[] b = new byte[1024];
				while ((len = is.read(b, 0, 1024)) != -1) {
					response.getOutputStream().write(b, 0, len);
				}
			}
		}
	}
}