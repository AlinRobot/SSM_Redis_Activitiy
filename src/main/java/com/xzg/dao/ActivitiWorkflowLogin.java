/**
 * 
 */
package com.xzg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xzg.domain.Group;
import com.xzg.domain.Node;
import com.xzg.domain.User;
/**
 * @author xzg
 * @TIME 2017��2��12��
 * ע��������غ�ʵ������
 */
public interface ActivitiWorkflowLogin {
	  /**
     * ��֤��¼,mybits���ݶ������ʱ�������ַ�ʽ1�����¡�2��where user_name = #{0} and user_area=#{1}#{0}������յ���dao���еĵ�һ��������
     * #{1}����dao���еڶ��������������һ������Ӽ��ɡ�3������Map�������.Public User selectUser(Map paramMap);
     * where user_name = #{userName��jdbcType=VARCHAR} and user_area=#{userArea,jdbcType=VARCHAR}
     */
	public int login(@Param("userId")String userId, @Param("password")String password) throws Exception;
	/**
	 * ��ȡ�û���ϸ��Ϣ��֤
	 */
	public User getUserInfo(String userId);
	/**
	 * �����û�id��ѯ�û����ڵ���
	 */
	public List<Group> getUserOfGroup(String userId);

	/**
	 * ����groupId��ѯ����ϸ��Ϣ
	 */
	public Group getGroupInfo(String roleId);
	/**
	 * �г����ڵ������û�
	 */
	public List<User> memberOfGroup(String roleId) ;

/**
 * �г������û�
 */
public List<User> getListUser() ;
/**
 * �г����н�ɫ
 */
public List<Group> getListGroup() ;
/*�����û�idɾ��*/
public void deleteUserById();
/*���ݽ�ɫidɾ��*/
public void deleteGroupById();
/*�޸��û�*/
public void updateUserByid(User user);
/*�޸���*/
public void updateGroupByid(Group group);

//z_tree���νṹչʾ��ɫ��Ȩ��
public List<Node> treeList(User user);
}


