package com.lin.service;  
  
import com.xzg.domain.User;
  
/** 
 * ���ܸ�Ҫ��UserService�ӿ��� 
 *  
 */  
public interface UserService {  
    User selectUserById(Integer userId);  
    int updateUser(User user);
}  