/**
 * 
 */
package com.xzg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.domain.Group;
import com.xzg.domain.User;

/**
 * @author hasee
 * @TIME 2016��12��9��
 * ע��������غ�ʵ������
 */
public interface GroupMapper {
	 // (Author) selectOne("selectAuthor",5);  
	public	 User getUserById(@Param("userId")long userId);   // (List<Author>) selectList(��selectAuthors��) 
	//ͨ���û���ѯ��ɫ
	public 	List<Group> getGroupList(@Param("userId")long userId);
	//������
	public 	Group getGroupById(String roleId);
	//ɾ�������
	public 	void deleteGroupByid(String roleId);
	//����M��Ϣ
	public 	int saveGroup(Group group);
	
}
