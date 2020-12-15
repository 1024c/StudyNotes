package com.tawe.service.edu.mapper;

import com.tawe.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tawe.service.edu.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo selectCoursePublishVoById(String id);
}
