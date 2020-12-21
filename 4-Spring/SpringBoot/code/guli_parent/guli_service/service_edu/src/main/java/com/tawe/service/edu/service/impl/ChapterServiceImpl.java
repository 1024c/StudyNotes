package com.tawe.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tawe.common.service.base.exception.CustomException;
import com.tawe.service.edu.entity.Chapter;
import com.tawe.service.edu.entity.Video;
import com.tawe.service.edu.mapper.ChapterMapper;
import com.tawe.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tawe.service.edu.service.VideoService;
import com.tawe.service.edu.vo.ChapterVo;
import com.tawe.service.edu.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {
        // 最终需要的数据列表
        List<ChapterVo> chapterVos = new ArrayList<>();
        // 获取章节信息
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByDesc("sort", "id");
        List<Chapter> chapters = baseMapper.selectList(queryWrapper);

        // 获取课时信息
        QueryWrapper<Video> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByDesc("sort", "id");
        List<Video> videos = videoService.list(queryWrapper1);

        // 填充章节 Vo 数据
        chapters.forEach(chapter -> {
            // 创建章节 Vo 对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVos.add(chapterVo);

            // 填充课时 Vo 数据
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            videos.forEach(video -> {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVos.add(videoVo);
                }
            });
            chapterVo.setChildren(videoVos);
        });

        // 创建可实 Vo 对象
        return chapterVos;
    }

    @Override
    public boolean removeChapterById(String id) {
        // 根据 id 查询是否存在视频, 如果有则提示用户尚有子节点 不可删除
        if (videoService.getCountByChapterId(id)) {
            throw new CustomException(200001, "该分类下存在视频课程, 请先删除视频课程");
        }
        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public boolean removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        Integer count = baseMapper.delete(queryWrapper);
        return count > 0;
    }
}
