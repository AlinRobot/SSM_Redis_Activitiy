/**
 * 
 */
package com.xzg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lin.service.RoomService;
import com.xzg.cache.GetCache;

/**
 * 
 * @description test controller
 */

@Controller
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService  roomService;
    
    @GetCache(name="room",value="id")
    @RequestMapping("selectByPrimaryKey")
    public @ResponseBody Object roomList(Integer id) throws Exception{  
        System.out.println("�Ѳ�ѯ�����ݣ�׼�����浽redis...  "+roomService.selectByPrimaryKey(id));
        return roomService.selectByPrimaryKey(id);
    }
    

}
