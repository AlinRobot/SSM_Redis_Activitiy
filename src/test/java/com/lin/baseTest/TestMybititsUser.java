/**
 * 
 */
package com.lin.baseTest;

import com.xzg.domain.User;
import com.xzg.managers.UserManager;

/**
 * @author hasee
 * @TIME 2016��12��28��
 * ע��������غ�ʵ������
 */
public class TestMybititsUser {

	public static void main(String[] args){
		UserManager um = new UserManager();
		User u = um.getUserById(10001);
		System.out.println(u.toString());
	}
}
