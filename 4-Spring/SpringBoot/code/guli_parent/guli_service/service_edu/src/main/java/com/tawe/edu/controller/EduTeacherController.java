package com.tawe.edu.controller;


import com.tawe.edu.entity.EduTeacher;
import com.tawe.edu.service.EduTeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-10-29
 */
@RestController
@RequestMapping("/edu/edu-teacher")
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @GetMapping("select-one")
    public EduTeacher selectOne(@RequestParam("id")String id) {
        return eduTeacherService.selectOne(id);
    }

    @GetMapping("select-all")
    public List<EduTeacher> selectAll() {
        return eduTeacherService.selectAll();
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteById(@PathVariable("id") String id) {
        return eduTeacherService.deleteById(id);
    }
}

