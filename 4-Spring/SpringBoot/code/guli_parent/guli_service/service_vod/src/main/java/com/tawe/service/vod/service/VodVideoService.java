package com.tawe.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoService
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 6:02 PM
 * @Version 1.0
 **/
public interface VodVideoService {
    String uploadVideo(MultipartFile file);
    void removeVideo(String videoId);
    void removeVideoList(List<String> videoIdList);
}
