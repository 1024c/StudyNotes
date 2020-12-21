package com.tawe.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.form.CourseInfoForm;
import com.tawe.service.edu.query.CourseQuery;
import com.tawe.service.edu.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    String updateCourseInfoById(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
