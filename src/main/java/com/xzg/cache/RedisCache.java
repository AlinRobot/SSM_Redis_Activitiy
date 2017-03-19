/**
 * 
 */
package com.xzg.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.xzg.publicUtil.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author hasee
 * @TIME 2017��3��20��
 * ע��������غ�ʵ������
 */
public class RedisCache {
    
    @Autowired
    private JedisPool jedisPool;
    

    //��redis�����в�ѯ�������л�
    public Object getDataFromRedis(String redisKey){
        //��ѯ
        Jedis jedis = jedisPool.getResource();
        byte[] result = jedis.get(redisKey.getBytes());
        
        //�����ѯû��Ϊ��
        if(null == result){
            return null;
        }
        
        //��ѯ���ˣ������л�
        return SerializeUtil.unSerialize(result);
    }
    
    //�����ݿ��в�ѯ�������ݷ���redis
    public void setDataToRedis(String redisKey, Object obj){
        
        //���л�
        byte[] bytes = SerializeUtil.serialize(obj);
        
        //����redis
        Jedis jedis = jedisPool.getResource();
        String success = jedis.set(redisKey.getBytes(), bytes);
        
        if("OK".equals(success)){
            System.out.println("���ݳɹ����浽redis...");
        }
    }
}