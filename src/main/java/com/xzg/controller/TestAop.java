/**
 * 
 */
package com.xzg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hasee
 * @TIME 2017��1��5��
 * ע��������غ�ʵ������
 */
@Controller
@RequestMapping("/emp")
public class TestAop {
@RequestMapping("/findemp.do")
public String find(){
	System.out.println("��ѯԱ����Ϣ���͵�ҳ��");
	return "emp/emp_list.jsp";
}
}
