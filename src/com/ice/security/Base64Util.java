package com.ice.security;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;

import com.ice.util.Util;

public class Base64Util {
	/**
     * 使用Base64 URL编码格式加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptURL(String data) throws Exception{
      return new String(Base64.getUrlEncoder().encodeToString(data.getBytes("UTF-8")));
    }
    
    /**
     * 使用Base64 URL编码格式解密
     * @param data
     * @return
     */
    public static String decryptURL(String data){
    	return new String(Base64.getUrlDecoder().decode(data));
    }

    /**
     * 使用BASE64解密信息
     *
     * @param data 待解密的信息
     * @return 解密后的信息
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
        // return (new Base64Encoder()).decode(data);
        return new String(Base64.getDecoder().decode(data.getBytes("UTF-8")));
    }

    /**
     * 使用BASE64加密信息
     *
     * @param data 待加密的信息
     * @return 加密后的信息
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        return new String(Base64.getEncoder().encodeToString(data.getBytes("UTF-8")));
    }

    /**
     * 将base64的加密后的数据对象进行解密
     *
     * @param obj
     * @throws Exception
     */
    public static void convertDecrypt(Object obj) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (!field.getName().contains("$") && !field.getName().equals("serialVersionUID")) {
                Method getMethod = obj.getClass().getDeclaredMethod("get" + Util.toUpper(field.getName()));
                if (getMethod != null) {
                    Object object = getMethod.invoke(obj);

                    String decrypt = decrypt((String) object);
                    System.out.println(field.getName() + "," + decrypt);
                    if (decrypt != null && !decrypt.isEmpty()) {
                        Method setmethod = obj.getClass().getDeclaredMethod("set" + Util.toUpper(field.getName()), String.class);
                        setmethod.invoke(obj, decrypt);
                    }
                }
            }
        }
    }

    /**
     * 将base64的加密后的数据集合进行解密
     * @param list
     * @throws Exception
     */
    public static List<?> convertList(List<?> list)  {
        for (Object t : list) {
            try {
                convertDecrypt(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
