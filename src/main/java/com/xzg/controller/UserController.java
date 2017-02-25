/**
 * 
 */
package com.xzg.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xzg.dao.UserDao;
import com.xzg.domain.User;
/**
 * @author hasee
 *@TIME2016��12��1��
 */
/* ����SpringMVC����ע��
@Controller
������ע��һ��bean ��spring ��������
@RequestMapping
��ע��Ϊ������ָ�����Դ�����Щ URL ����
@RequestBody
��ע�����ڶ�ȡRequest�����body�������ݣ�ʹ��ϵͳĬ�����õ�HttpMessageConverter���н�����Ȼ�����Ӧ�����ݰ󶨵�Ҫ���صĶ����� ,
�ٰ�HttpMessageConverter���صĶ������ݰ󶨵� controller�з����Ĳ�����
@ResponseBody
��ע�����ڽ�Controller�ķ������صĶ���ͨ���ʵ���HttpMessageConverterת��Ϊָ����ʽ��д�뵽Response�����body������
@ModelAttribute ������
�ڷ���������ʹ�� @ModelAttribute ע�⣺Spring MVC �ڵ���Ŀ�괦����ǰ��������������ڷ������ϱ�ע��@ModelAttribute �ķ���
�ڷ��������ǰʹ�� @ModelAttribute ע�⣺���Դ����������л�ȡ������ģ�������л�ȡ�����ٽ�������� �C�󶨵������У��ٴ�����ν�������ζ�����ӵ�ģ���� 
@RequestParam��
�ڴ�������δ�ʹ�� @RequestParam ���԰������ �����ݸ����󷽷�
@PathVariable
�� URL ռλ�������
@ExceptionHandler
ע�⵽�����ϣ������쳣ʱ��ִ�и÷���
@ControllerAdvice
ʹһ��Contoller��Ϊȫ�ֵ��쳣�����࣬
������@ExceptionHandler����ע��ķ������Դ�������Controller�������쳣*/
@Controller  
public class UserController {  
    @Resource  
    private UserDao userDao; 
    @RequestMapping(value="/showUser.do",method = RequestMethod.GET) 
    @ResponseBody  
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("view/index");   
      User  user = userDao.selectUserById(10000);  
        mav.addObject("user", user);   
        return mav;    
    }
    @RequestMapping( "/updateUser.do")
    @ResponseBody  //��springMVC���ṩ��JSON��Ӧ��֧��
    public ModelAndView showUdateUser(User user){
    	 System.out.println(user);
       @SuppressWarnings("unused")
	int a = userDao.updateUser(user); 
     return   getIndex();
    } 
    @RequestMapping("/test.do")
    @ResponseBody  //��springMVC���ṩ��JSON��Ӧ��֧��
    public Map<String,Object>  test2(@RequestBody String name){
   /* 	model�ǽ�������ӵ�request��Springmvc����һ������redirectattributes����������flash attribute��
    	������ǽ����ݴ���session����Ҫ�ǽ��redirect�����*/
    	Map<String,Object> mapout=new HashMap<String, Object>();
    	mapout.put("id", 16);
    	mapout.put("name",name.trim());
    	return mapout;
    }
    @RequestMapping(value="/leavestart.do",method = RequestMethod.GET)
    public ModelAndView getLeaveOff(){      
        ModelAndView mav = new ModelAndView("leaveoff/leavestart");   
        return mav;    
    }
}  


