package com.tawe.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.tawe.service.vod.util.AliyunVodUtil;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName AliyunVodUtils
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 4:47 PM
 * @Version 1.0
 **/
public class AliyunVodTest {

    String accessKeyId = "你的accessKeyId";
    String accessKeySecret = "你的accessKeySecret";

    private DefaultAcsClient client;

    public AliyunVodTest() {
        client = AliyunVodUtil.initVodClient(accessKeyId, accessKeySecret);
    }

    @Test
    public void testGetVideoAuth() throws ClientException {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("videoId");
        response = client.getAcsResponse(request);
        String auth = response.getPlayAuth();
        String title = response.getVideoMeta().getTitle();
        System.out.println(String.format("auth: %s\ntitle: %s", auth, title));
    }

    @Test
    public void testGetVideoInfo() throws ClientException {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        request.setVideoId("videoId");
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        playInfoList.forEach(playInfo -> {
            String playURL = playInfo.getPlayURL();
            System.out.println(playURL);
        });
        String title = response.getVideoBase().getTitle();
        System.out.println("Title: " + title);
    }

    @Test
    public void testUploadVideo() throws ClientException {
        String title = "title";
        String fileName = "filePath";

        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        CreateUploadVideoResponse response;

        request.setTitle(title);
        request.setFileName(fileName);
        request.setFileSize(1024 * 1024L);

        response = client.getAcsResponse(request);
        String videoId = response.getVideoId();
        System.out.println("videoId: " + videoId);
    }
}
