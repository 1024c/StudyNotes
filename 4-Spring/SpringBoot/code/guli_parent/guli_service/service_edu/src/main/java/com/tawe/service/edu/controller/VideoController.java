package com.tawe.service.edu.controller;


import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.entity.Video;
import com.tawe.service.edu.form.VideoInfoForm;
import com.tawe.service.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-12-14
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("save-video-info")
    public ResultEntity save(
            @RequestBody VideoInfoForm videoInfoForm
            ) {
        videoService.saveVideoInfo(videoInfoForm);
        return ResultEntity.ok();
    }

    @GetMapping("video-info/{id}")
    public ResultEntity getVideoInfoById(
            @PathVariable String id
    ) {
        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return ResultEntity.ok().data("item", videoInfoForm);
    }

    @PutMapping("update-video-info/{id}")
    public ResultEntity updateVideoInfoById(
            @PathVariable String id,
            @RequestBody VideoInfoForm videoInfoForm
    ) {
        videoService.updateVideoInfoById(videoInfoForm);
        return ResultEntity.ok();
    }

    @DeleteMapping("{id}")
    public ResultEntity removeById(
            @PathVariable String id
    ) {
        boolean result = videoService.removeVideoById(id);
        if (result) {
            return ResultEntity.ok();
        } else {
            return ResultEntity.error().message("课时信息删除失败");
        }
    }

}

