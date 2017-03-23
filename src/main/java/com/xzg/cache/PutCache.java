/**
 * 
 */
package com.xzg.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/** 
 * �Զ���ע��,���ڲ�ѯʹ�û���ķ��������ע�� 
 * @author Chenth 
 */  
@Retention(RetentionPolicy.RUNTIME)   // ע�����class�ֽ����ļ��д��ڣ�������ʱ����ͨ�������ȡ��
@Target({ElementType.METHOD})  
public @interface PutCache {  
    String name() default "";  
    String value() default "";  
}