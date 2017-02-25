/**
 * 
 */
package com.xzg.workFlow.util;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.springframework.stereotype.Service;

import com.xzg.dao.UserMapper;

/**
 * @author hasee
 * @TIME 2016��12��26��
 * ע��������غ�ʵ������
 */
//ʹ���Զ����û���ɫһ��ֻ�踲��findUserById��findGroupsByUser����
@Service
public class CustomUserEntityManager  extends UserEntityManager{
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory  
            .getLog(CustomUserEntityManager.class);
	@Resource(name="userManager")
 	private UserMapper	 mapper;
    @Override
	  public UserEntity findUserById(final String userCode) {
	        if (userCode == null)
	            return null;
	        try {
	            UserEntity userEntity = null;
	            com.xzg.domain.User bUser = mapper.getUserById(Long.valueOf(userCode));
	            userEntity = ActivitiUtils.toActivitiUser(bUser);
	            return userEntity;
	        } catch ( Exception e) {
	           e.printStackTrace();
	        }
	       return null ;
	    }
    
	@Override
    public List<Group> findGroupsByUser(final String userCode) {
        if (userCode == null)
            return null;
        com.xzg.domain.User   user = mapper.getUserById(Long.valueOf(userCode));
        List<com.xzg.domain.Group> bGroups =mapper.getGroupList(user.getUserId());
        List<Group> gs = null;
        gs = ActivitiUtils.toActivitiGroups(bGroups);
        return gs;
    }
	
		@Override
		public long findUserCountByQueryCriteria(UserQueryImpl query) {
			// TODO Auto-generated method stub
			 return super.findUserCountByQueryCriteria(query);
		}
		@Override
		public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId,
				String key) {
			// TODO Auto-generated method stub
			return super.findUserInfoByUserIdAndKey(userId, key);
		}

		@Override
		public List<String> findUserInfoKeysByUserIdAndType(String userId,
				String type) {
			// TODO Auto-generated method stub
			return super.findUserInfoKeysByUserIdAndType(userId, type);
		}

		@Override
		public Boolean checkPassword(String userId, String password) {
			// ��ֹactiviti���ø÷���
			 throw new RuntimeException("not implement method.");
		}

		@Override
		public List<org.activiti.engine.identity.User> findPotentialStarterUsers(String proceDefId) {
			// TODO Auto-generated method stub
			 throw new RuntimeException("not implement method.");
		}
	 
}
