/**
 * 
 */
package com.xzg.domain;

/**
 * @author hasee
 * @TIME 2016��12��27��
 * ע��������غ�ʵ������
 */
public class User_Group {
private Integer	id  ;
	private String userId	;
	private String userName;
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "�û�id��"+getUserId()+"=="+"�û���ɫ��"+getRoleId();
	}
	private String roleId ;
   private String roleInfo;
    private String  roleName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
