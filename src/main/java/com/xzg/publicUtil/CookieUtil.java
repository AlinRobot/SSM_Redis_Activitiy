/**
 * 
 */
package com.xzg.publicUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.codec.Base64;
/**
 * @author xzg
 * @TIME 2017��3��8��
 * ע��������غ�ʵ������
 */
public class CookieUtil {
       //����cookieʱ��cookieName
       private final static String cookieDomainName = "cn.xzg";
      
       //����cookieʱ����վ�Զ���
       public  final static String webKey = "myinfo";
      
//����cookie��Ч����һСʱ��������Ҫ�Զ���
       private final static long cookieMaxAge = 60 * 5  ;//����5����        
       public static void saveCookie(String username,String password, HttpServletResponse response) {
              //cookie����Ч��
              long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
              //MD5�����û���ϸ��Ϣ
              //���Ƚ��û��������MD5���ܵõ����ܺ���û�����
              String passwordMd5=getMD5(password);
              //�γ�MD5����
              String cookieValueWithMd5 =getMD5(username +passwordMd5+webKey);
              //��Ҫ�������������Cookieֵ
              String cookieValue = username + ":" + validTime + ":" + cookieValueWithMd5;
              //��һ�ζ�Cookie��ֵ����BASE64���� Base64.encodeBase64(src.getBytes());
              String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
              //��ʼ����Cookie
              Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
              cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
              //cookie��Ч·������վ��Ŀ¼
              cookie.setPath("/");
              //��ͻ���д��
              response.addCookie(cookie);
       }

	/**
	 * @param string
	 * @return
	 */
	public static String getMD5(String string) {
		// TODO Auto-generated method stub
		 //ȷ�����㷽��
	    try { 
        MessageDigest md5=MessageDigest.getInstance("MD5");
        // ������ַ���ת�����ֽ�����  
        byte[] inputByteArray = string.getBytes();  
        // inputByteArray�������ַ���ת���õ����ֽ�����  
        md5.update(inputByteArray);  
        // ת�������ؽ����Ҳ���ֽ����飬����16��Ԫ��  
        byte[] resultByteArray = md5.digest();  
        // �ַ�����ת�����ַ�������  
        return byteArrayToHex(resultByteArray); 
        } catch (NoSuchAlgorithmException e) {  
            return null;  
         }  
	}
	 public static String byteArrayToHex(byte[] byteArray) {  
	        // ���ȳ�ʼ��һ���ַ����飬�������ÿ��16�����ַ�  
	        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };  
	        // newһ���ַ����飬�������������ɽ���ַ����ģ�����һ�£�һ��byte�ǰ�λ�����ƣ�Ҳ����2λʮ�������ַ���2��8�η�����16��2�η�����  
	        char[] resultCharArray =new char[byteArray.length * 2];  
	        // �����ֽ����飬ͨ��λ���㣨λ����Ч�ʸߣ���ת�����ַ��ŵ��ַ�������ȥ  
	        int index = 0; 
	        for (byte b : byteArray) {  
	           resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
	           resultCharArray[index++] = hexDigits[b& 0xf];  
	        }
	        // �ַ�������ϳ��ַ�������  
	        return new String(resultCharArray);  
	    }
	
	 /**
	      * ɾ��cookie
	      * 
	      * @param response
	      * @param name
	     */
	  public static void removeCookie(HttpServletResponse response, String name) {
	         Cookie uid = new Cookie(name, null);
	         uid.setPath("/");
	         uid.setMaxAge(0);
	        response.addCookie(uid);
    }
	  /**
	    * ��ȡcookieֵ
	   * @param request
	  * @return
	*/
	    public static String getUid(HttpServletRequest request,String cookieName) {
	        Cookie cookies[] = request.getCookies();
	       for (Cookie cookie : cookies) {
	              if (cookie.getName().equals(cookieName)) {
	                return cookie.getValue();
	               }
	           }
	         return null;
	         }
}