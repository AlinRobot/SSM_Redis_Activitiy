/**
 * 
 */
package com.lin.workLeave;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @author hasee
 * @TIME 2016��12��26��
 * ע��������غ�ʵ������
 */
public class TaskListenerImpl implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * ִ�м�����ʵ�ֵĲ�������������࣬����ִ�м�����Ҫʵ�ֵĽӿ���ExecutionLintener
	 * @see org.activiti.engine.delegate.TaskListener#notify(org.activiti.engine.delegate.DelegateTask)
	 */
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		//ָ��������  
        delegateTask.addCandidateUser("�����");  
        delegateTask.addCandidateUser("��˽�");  
	}

}
