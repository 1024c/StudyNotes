package com.tawe.service.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtil
 * @Description TODO
 * @Author davidt
 * @Date 11/12/2020 3:38 PM
 * @Version 1.0
 **/

@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;
    @Value("${aliyun.oss.file.filehost}")
    private String filehost;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST;



    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = this.endpoint;
        ACCESS_KEY_ID = this.keyid;
        ACCESS_KEY_SECRET = this.keysecret;
        BUCKET_NAME = this.bucketname;
        FILE_HOST = this.filehost;
    }
}
