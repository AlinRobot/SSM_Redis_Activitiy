/**
 * 
 */
package com.lin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xzg.dao.RoomMapper;
import com.xzg.domain.User;

/**
 * @author hasee
 * @TIME 2017��3��20��
 * ע��������غ�ʵ������
 */
@Service("roomServiceImple")
public class RoomServiceImpl implements RoomService{

    @Resource
    private RoomMapper mapper;
    
    @Override
    public int insert(User user) throws Exception {
        
        return mapper.insert(user);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) throws Exception {
        
        return mapper.selectByPrimaryKey(userId);
    }
    
}