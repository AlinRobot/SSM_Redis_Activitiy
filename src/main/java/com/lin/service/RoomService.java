/**
 * 
 */
package com.lin.service;

import com.xzg.domain.User;

/**
 * @author hasee
 * @TIME 2017��3��20��
 * ע��������غ�ʵ������
 */
public interface RoomService {

	 
    int insert(User user)throws Exception;
    
    User selectByPrimaryKey(Integer userId)throws Exception;
    
}
