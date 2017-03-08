/**
 * 
 */
package com.xzg.workFlow.util;

import javax.annotation.Resource;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

/**
 * @author hasee
 * @TIME 2016��12��26��
 * ע��������غ�ʵ������
 */
//���������
public class CustomGroupEntityManagerFactory implements SessionFactory {
	@Resource
	private CustomGroupEntityManager customGroupEntityManager; 
	/*@Autowired
	    public void setGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
	        this.customGroupEntityManager = customGroupEntityManager;
	    }*/
	public Class<?> getSessionType() {
		// TODO Auto-generated method stub
		return GroupIdentityManager.class;
	}
	public Session openSession() {
		// TODO Auto-generated method stub
		return customGroupEntityManager;
	}

}
