package com.tawe.service.edu.service;

import com.tawe.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    boolean removeChapterByCourseId(String courseId);

}
