/**
 * 
 */
package com.xzg.dao;

import java.util.List;

import com.xzg.domain.Node;
import com.xzg.domain.User;

/**
 * @author hasee
 *
 */
public interface UserDao {  
    /** 
     *  
     * @author linbingwen 
     * @since 2015��9��28�� 
     * @param userId 
     * @return 
     */  
    public User selectUserById(Integer userId); 
    public int updateUser(User user);
    public User selectLeaveUser(User user);
    //z_tree���νṹչʾ
    public List<Node> treeList();
    
    
    
    
}