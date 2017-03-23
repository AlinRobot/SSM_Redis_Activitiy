/**
 * 
 */
package com.xzg.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import javax.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.xzg.cache.GetCache;
import com.xzg.cache.RedisCache;
import com.xzg.publicUtil.CookieUtil;
/**@AspectJ�����������ͨ��@Compenentע���ʶ��ΪSpring����Bean��
��@Aspectע�ⲻ�ܱ�Spring�Զ�ʶ��ע��ΪBean������ͨ��@Componentע�������
*/
@Component
@Aspect//��������ʹ����aop
public class PutCacheAOP  {  
    @Resource(name="redisTemplate")
    private RedisTemplate<Serializable, Object> redisTemplate;
    @Resource(name="redisCache")
    private RedisCache redisCache;
    @Pointcut("@annotation(com.xzg.cache.PutCache)")  
    public void putCache(){
        System.out.println("����һ�������");  
    }  
    /** 
     * �����б�ע@putCache�ĵط����� 
     * @param joinPoint 
     */
    @Around("putCache()")
    public Object beforeExec(ProceedingJoinPoint joinPoint){  
        //ǰ�ã���redis�в�ѯ����
        System.out.println("���ô�redis�в�ѯ�ķ���...");
        //redis��key��ʽ��    id
        String redisKey = getCacheKey(joinPoint);
        Object object = null;
        try {
        	  //ɾ����������
            redisCache.removeDataToRedis(redisKey);
            //ִ��aop����֮��ķ���
            object = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return object;
    }
    /**
     * �����������������Ͳ���ֵ��ȡΨһ�Ļ����
     * @return ��ʽΪ "����.����.������.��������.����ֵ"������ "your.package.SomeService.getById(int).123"
     */
    private String getCacheKey(ProceedingJoinPoint joinPoint) {
        MethodSignature ms=(MethodSignature) joinPoint.getSignature();  
        Method method=ms.getMethod();  
        String ActionName = method.getAnnotation(GetCache.class).name();  
        String fieldList = method.getAnnotation(GetCache.class).value();  
        //System.out.println("ǩ����"+ms.toString());
        for (String field:fieldList.split(",")) //field��ʾ����Ĳ���ֵ�����ж��
             ActionName +="."+field;
        //�Ȼ�ȡĿ�귽������
        String id = null;
        Object[] args = joinPoint.getArgs();//[10005]
        if (args != null && args.length > 0) {
            id = String.valueOf(args[0]);
        }
        ActionName += "="+id;
        String redisKey = CookieUtil.getMD5(ms+"."+ActionName);//ʹ��MD5���ܺ����Ϊ������
        System.out.println("��ǰ������keyֵ=="+redisKey);//Object com.xzg.controller.RoomController.roomList(Integer).user.id=10005
        return redisKey;
    }
    /*public void setRedisTemplate( 
            RedisTemplate<Serializable, Object> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }*/
}