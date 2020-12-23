package com.tawe.service.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.entity.Course;
import com.tawe.service.edu.entity.Teacher;
import com.tawe.service.edu.service.CourseService;
import com.tawe.service.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author davidt
 * @Date 12/23/2020 6:05 PM
 * @Version 1.0
 **/

@RestController
@CrossOrigin
@RequestMapping("/edu/front/index")
public class IndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("index")
    public ResultEntity index() {

        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("id");
        courseWrapper.last("limit 8");
        List<Course> courses = courseService.list(courseWrapper);

        QueryWrapper<Teacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("id");
        teacherWrapper.last("limit 4");
        List<Teacher> teachers = teacherService.list(teacherWrapper);

        return ResultEntity.ok().data("courses", courses).data("teachers", teachers);
    }
}
