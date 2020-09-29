package com.tawe.crowd.util;

import com.tawe.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName CrowdUtil
 * @Description TODO
 * @Author Administrator
 * @Date 9/24/2020 4:57 PM
 * @Version 1.0
 **/
public class CrowdUtil {
    /**
     * 判断当前方法是返回 JSON 还是 页面
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        // 1. 获取请求头消息
        String accept = request.getHeader("Accept");
        String xRequest = request.getHeader(("X-Requested-With"));

        // 2. 检查并返回
        return (accept != null && accept.length() > 0 && accept.contains("application/json") ||
                xRequest != null && xRequest.length() > 0 && "XMLHttpRequest".equals(xRequest));

    }

    /**
     * MD5 加密算法
     * @param source
     * @return
     */
    public static String md5(String source) {
        // 1. 判断 soruce 是否有效;
        if (source == null || source.length() == 0) {
            // 2. 如果不是有效的字符串抛出异常;
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE.toString());
        }

        // 3. 获取 MessageDigest 对象;
        try {
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4. 获取明文字符串对应的字节数组;
            byte[] input = source.getBytes();
            // 5. 执行加密;
            byte[] output = messageDigest.digest(input);
            // 6. 创建 BigInteger 对象;
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7. 按照 16 进制将 BigInteger 的值转化为字符串;
            int radix = 16;
            return bigInteger.toString(radix).toUpperCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
