package com.tawe.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tawe.common.service.base.exception.CustomException;
import com.tawe.service.edu.entity.Video;
import com.tawe.service.edu.form.VideoInfoForm;
import com.tawe.service.edu.mapper.VideoMapper;
import com.tawe.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author tawe
 * @since 2020-12-14
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public boolean getCountByChapterId(String id) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper();
        queryWrapper.eq("chapter_id", id);
        int count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.save(video);

        if (!result) {
            throw new CustomException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        Video video = this.getById(id);
        if (video == null) {
            throw new CustomException(20001, "数据不存在");
        }
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.updateById(video);
        if (!result) {
            throw new CustomException(20001, "课时信息保存失败");
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}
