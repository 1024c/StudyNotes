package com.tawe.service.edu.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tawe.common.service.base.exception.CustomException;
import com.tawe.service.edu.entity.Video;
import com.tawe.service.edu.form.VideoInfoForm;
import com.tawe.service.edu.mapper.VideoMapper;
import com.tawe.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tawe.service.edu.service.VodClient;
import com.tawe.service.vod.util.AliyunVodUtil;
import com.tawe.service.vod.util.ConstantPropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private VodClient vodClient;

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
        // 删除视频
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        if (StringUtils.isEmpty(videoSourceId)) {
            vodClient.removeVideo(videoSourceId);
        }
        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public boolean removeVideoByCourseId(String courseId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.select("video_source_id");
        List<Video> videoList = baseMapper.selectList(queryWrapper);

        List<String> videoSourceIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            Video video = videoList.get(i);
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        if (videoSourceIdList.size() > 0) {
           vodClient.removeVideoList(videoSourceIdList);
        }
        QueryWrapper<Video> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        int count = baseMapper.delete(queryWrapper1);
        return count > 0;
    }

    @Override
    public void removeVideoList(List<String> videoIdList) {
        DefaultAcsClient client = AliyunVodUtil.initVodClient(
                ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET
        );
        String str = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ",");
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(str);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("RequestId = " + response.getRequestId() + "\n");
        } catch (ClientException e) {
            throw new CustomException(20001, "视频删除失败");
        }


    }
}
