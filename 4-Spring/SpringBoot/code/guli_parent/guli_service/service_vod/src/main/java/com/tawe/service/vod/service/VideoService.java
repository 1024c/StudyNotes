package com.tawe.service.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VideoService
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 6:02 PM
 * @Version 1.0
 **/
public interface VideoService {
    String uploadVideo(MultipartFile file);
    void removeVideo(String videoId);
}
