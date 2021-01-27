package com.home.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 尚筹网通用工具类
 *
 * @author 冯根源
 * @date 2021/1/26 21:04
 */
public class CrowdUtil {

    /**
     * 对明文字符串进行MD5加密
     * @param source 传入的明文字符串
     * @return
     */
    public static String md5(String source){
        // 1.判断source是否有效
        if (source==null||source.length()==0){
            //2.如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.将铭文字符串对应的字节数组 执行加密
            byte[] output= messageDigest.digest(source.getBytes());
            // 5.创建BigInteger对象
            int sigNum = 1;
            BigInteger bigInteger = new BigInteger(sigNum, output);
            // 6.按照16进制将bigInteger转化为字符串
            int radix =16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 判断请求类型方法
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1.获取请求头消息
        String acceptHeader = request.getHeader("Accept");
        String XRequestedHeader = request.getHeader("X-Requested-With");


        return ((acceptHeader != null && acceptHeader.contains("application/json"))
                ||
                (XRequestedHeader != null && XRequestedHeader.equals("XMLHttpRequest")));


    }
}
