/**
 * 
 */
package com.xzg.workFlow.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xzg.dao.GroupMapper;

/**
 * @author hasee
 * @TIME 2016��12��26��
 * ע��������غ�ʵ������
 */
//ʹ���Զ����û���ɫһ��ֻ�踲��findGroupById��findGroupsByUser����
@Service
public class CustomGroupEntityManager extends GroupEntityManager {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory  
            .getLog(CustomUserEntityManager.class);
	
	 @Resource(name="groupManager")
	 	private GroupMapper	 mapper;
	 //activiti5.20��Դ��GroupEntityManager�����Ѿ�������findGroupById����
	 public GroupEntity findGroupById(final String groupCode) {
        if (groupCode == null)
            return null;
        try {
            com.xzg.domain.Group bGroup = mapper.getGroupById(groupCode);
            GroupEntity e = new GroupEntity();
            e.setRevision(1);
            // activiti��3��Ԥ����������ͣ�security-role��assignment��user  
            // ���ʹ��Activiti  
            // Explorer����Ҫsecurity-role���ܿ���manageҳǩ����Ҫassignment����claim����  
            e.setType("assignment");
            e.setId(bGroup.getRoleId());
            e.setName(bGroup.getRoleName());
            return e;
        } catch (EmptyResultDataAccessException e) {
        	
            return null;
        }
    }

    @Override
    public List<Group> findGroupsByUser(final String userCode) {
        if (userCode == null)
            return null;
       com.xzg.domain.User user = mapper.getUserById(Long.valueOf(userCode));
        List<com.xzg.domain.Group> bGroupList = mapper.getGroupList(user.getUserId());
        List<Group> gs = new ArrayList<Group>();
        GroupEntity g;
        for (com.xzg.domain.Group bGroup : bGroupList) {
            g = new GroupEntity();
            g.setRevision(1);
            g.setType("assignment");
            g.setId(bGroup.getRoleId());
            g.setName(bGroup.getRoleName());
            gs.add(g);
        }
        return gs;
    }

	public void insertGroup(Group group) {
		throw new RuntimeException("not implement method.");//�����û���ҵ���߼��д���ʹ��activiti
	}

	@Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		return  super.findGroupByQueryCriteria(query, page);
      //  throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
    	return super.findGroupCountByQueryCriteria(query);
    }
}
