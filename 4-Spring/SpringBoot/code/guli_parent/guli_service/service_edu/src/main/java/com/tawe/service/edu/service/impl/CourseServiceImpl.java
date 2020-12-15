package com.tawe.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.common.service.base.exception.CustomException;
import com.tawe.common.utils.ResultCode;
import com.tawe.service.edu.entity.Course;
import com.tawe.service.edu.entity.CourseDescription;
import com.tawe.service.edu.form.CourseInfoForm;
import com.tawe.service.edu.mapper.CourseMapper;
import com.tawe.service.edu.query.CourseQuery;
import com.tawe.service.edu.service.CourseDescriptionService;
import com.tawe.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tawe.service.edu.vo.CoursePublishVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        // 保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.save(course);
        if (!resultCourseInfo) {
            throw new CustomException(ResultCode.ERROR.getCode(), "课程信息保存失败");
        }

        // 保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultCourseDescription = courseDescriptionService.save(courseDescription);
        if (!resultCourseDescription) {
            throw new CustomException(ResultCode.ERROR.getCode(), "课程详情信息保存失败");
        }
        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        // Course course = this.getById(id);
        Course course = baseMapper.selectById(id);
        if (course == null) {
            throw new CustomException(ResultCode.ERROR.getCode(), "数据不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);

        CourseDescription courseDescription = courseDescriptionService.getById(id);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public String updateCourseInfoById(CourseInfoForm courseInfoForm) {
        // 保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if (!resultCourseInfo) {
            throw new CustomException(ResultCode.ERROR.getCode(), "课程信息保存失败");
        }
        // 保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseInfoForm.getDescription());
        boolean resultCourseDescription = courseDescriptionService.updateById(courseDescription);
        if (!resultCourseDescription) {
            throw new CustomException(ResultCode.ERROR.getCode(), "课程详情信息保存失败");
        }

        return course.getId();
    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        Integer count = baseMapper.updateById(course);
        return count > 0;
    }

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
