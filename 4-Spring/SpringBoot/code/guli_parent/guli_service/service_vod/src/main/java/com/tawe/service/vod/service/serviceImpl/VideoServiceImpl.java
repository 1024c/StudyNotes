package com.tawe.service.vod.service.serviceImpl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.tawe.common.service.base.exception.CustomException;
import com.tawe.service.vod.service.VideoService;
import com.tawe.service.vod.util.AliyunVodUtil;
import com.tawe.service.vod.util.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VideoServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 6:03 PM
 * @Version 1.0
 **/
@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideo(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(originalFileName);

        DefaultAcsClient client = AliyunVodUtil.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        CreateUploadVideoResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomException(20001, "Aliyun Vod 服务上传失败!");
        }
        return response.getVideoId();
    }

    @Override
    public void removeVideo(String videoId) {
        DefaultAcsClient client = AliyunVodUtil.initVodClient(
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET
        );
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            throw new CustomException(20001, "视频删除失败!");
        }
    }
}
