package com.tawe.service.vod.controller;

import com.tawe.common.utils.ResultEntity;
import com.tawe.service.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VideoController
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 6:19 PM
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping(value ="/vod/video/")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public ResultEntity uploadVideo(
            @RequestParam("file")MultipartFile file
            ) {
        String videoId = videoService.uploadVideo(file);
        return ResultEntity.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("{videoId}")
    public ResultEntity removeVideo(
            @PathVariable("videoId") String videoId
    ) {
        videoService.removeVideo(videoId);
        return ResultEntity.ok().message("视频删除成功!");
    }
}
