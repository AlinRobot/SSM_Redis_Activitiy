/**
 * 
 */
package com.lin.workLeave;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author hasee
 * @TIME 2016��12��26��
 * ע��������غ�ʵ������
 */
public class TestEndListener  implements ExecutionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		execution.getVariable("");
		System.out.println("���Լ������Ƿ�ִ����������������������");
	}

}
