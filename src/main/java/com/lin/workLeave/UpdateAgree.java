/**
 * 
 */
package com.lin.workLeave;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author hasee
 * @TIME 2016��12��20��
 * ע��������غ�ʵ������
 */
public class UpdateAgree implements JavaDelegate  {

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.JavaDelegate#execute(org.activiti.engine.delegate.DelegateExecution)\
	 * ������һ����Ҫʹ�ó�Ա����
	 */
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//ִ�����ݿ��еĲ���������ٵ�״̬�޸�Ϊ������
		//String var = (String) execution.getVariable("input"); 
		//var = var.toUpperCase();  
		//execution.setVariable("input", var); 
		System.out.println("====================�������м���������Service TaskUPdateOne������============================");
	}

}
