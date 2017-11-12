package com.liang.common.util.sys;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * description  MD5加密工具类
 * @author      周强
 * @date        2016年1月25日 上午11:00:39
 * @version     1.0
 * copyright    gc technology
 */
public class MD5Util {
	private final static Logger log = Logger.getLogger(MD5Util.class);
	 public static String getMD5Str(String str) {   
         MessageDigest messageDigest = null;   
   
        try {   
             messageDigest = MessageDigest.getInstance("MD5");   
   
             messageDigest.reset();   
   
             messageDigest.update(str.getBytes("UTF-8"));   
         } catch (NoSuchAlgorithmException e) {   
             log.error("NoSuchAlgorithmException caught!");   
         } catch (UnsupportedEncodingException e) {   
             e.printStackTrace();   
         }   
   
        byte[] byteArray = messageDigest.digest();   
   
         StringBuffer md5StrBuff = new StringBuffer();   
   
        for (int i = 0; i < byteArray.length; i++) {               
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)   
                 md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));   
            else   
                 md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));   
         }   
        return md5StrBuff.toString();   
     }  
}