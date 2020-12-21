package com.tawe.service.edu.controller;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.entity.Course;
import com.tawe.service.edu.form.CourseInfoForm;
import com.tawe.service.edu.query.CourseQuery;
import com.tawe.service.edu.service.CourseService;
import com.tawe.service.edu.vo.CoursePublishVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
@Api("课程管理")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public ResultEntity saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm
            ) {
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        if (StringUtils.isEmpty(courseId)) {
            return ResultEntity.error().message("保存失败");
        } else {
            return ResultEntity.ok().data("courseId", courseId);
        }
    }

    @ApiOperation(value = "根据 ID 查询课程")
    @GetMapping("course-info/{id}")
    public ResultEntity getCourseInfo(
            @ApiParam(name = "id", value = "课程 ID", required = true)
            @PathVariable String id) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        return ResultEntity.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public ResultEntity updateCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm,
            @ApiParam(name = "id", value = "课程 ID", required = true)
            @PathVariable String id) {
        String courseId = courseService.updateCourseInfoById(courseInfoForm);
        return ResultEntity.ok().data("courseId", courseId);
    }

    @GetMapping("course-publish-info/{id}")
    public ResultEntity getCoursePublishVoById(
            @PathVariable String id
    ) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(id);
        return ResultEntity.ok().data("item", coursePublishVo);
    }

    @PutMapping("publish-course/{id}")
    public ResultEntity publishCourseById(
            @PathVariable String id
    ) {
        boolean result = courseService.publishCourseById(id);
        return result ? ResultEntity.ok() : ResultEntity.error().message("保存课程信息失败");
    }

    @GetMapping("{page}/{limit}")
    public ResultEntity pageQuery(
            @PathVariable Long page,
            @PathVariable Long limit,
            CourseQuery courseQuery
    ) {
        Page<Course> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return ResultEntity.ok().data("total", total).data("items", records);
    }

    @DeleteMapping("{id}")
    public ResultEntity removeById(
            @PathVariable String id
    ) {
        boolean result = courseService.removeCourseById(id);
        return result ? ResultEntity.ok() : ResultEntity.error().message("删除失败");
    }
}

