/**
 * 
 */
package com.xzg.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author hasee
 * @TIME 2017��2��21��
 * ע��������غ�ʵ������
 */
public class SessionListener  implements HttpSessionBindingListener{
	private ServletContext application;
	
	public SessionListener(ServletContext application) {
		// TODO Auto-generated constructor stub
		this.application = application;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("======================��⵽���û��ˣ�=====================");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("======================��⵽������ˣ�=====================");
		//��ʱ�Ƴ�ȫ��sessionId
		if(application != null&&application.getAttribute("userid") != null){
			application.removeAttribute("userid");
		}
	}

}
