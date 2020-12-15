package com.tawe.service.edu.service;

import com.tawe.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author tawe
 * @since 2020-12-14
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String id);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);
}
