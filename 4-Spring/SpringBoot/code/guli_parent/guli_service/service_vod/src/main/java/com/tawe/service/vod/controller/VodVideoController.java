package com.tawe.service.vod.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.tawe.common.utils.ResultEntity;
import com.tawe.service.vod.service.VodVideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
public class VodVideoController {
    @Autowired
    private VodVideoService videoService;

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

    @DeleteMapping("delete-batch")
    public ResultEntity removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List videoIdList){

        videoService.removeVideoList(videoIdList);
        return ResultEntity.ok().message("视频删除成功");
    }
}
