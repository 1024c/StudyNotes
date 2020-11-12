package com.tawe.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.tawe.service.oss.service.FileService;
import com.tawe.service.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName FileServiceImpl
 * @Description 阿里云文件上传
 * @Author davidt
 * @Date 11/12/2020 3:45 PM
 * @Version 1.0
 **/

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;

        String uploadUrl = "";

        // 创建 OSSClinet 实例
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 判断 bucket 是否存在(类似于 Azure 的 Container 应该)
        if (!oss.doesBucketExist(bucketName)) {
            oss.createBucket(bucketName);
            oss.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        try(InputStream inputStream = file.getInputStream();) {
            String filePath = new DateTime().toString("yyyy/MM/dd");

            String original = file.getOriginalFilename();
            assert original != null;
            String fileType = original.substring(original.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString();
            String newName = fileName + fileType;
            String fileUrl = filePath + "/" + newName;
            uploadUrl = "http://" + fileHost + "/" + fileUrl;
            oss.putObject(bucketName, fileUrl, inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        oss.shutdown();
        return uploadUrl;
    }
}
