package com.tawe.service.edu.controller;


import com.tawe.common.utils.ResultEntity;
import com.tawe.common.utils.ResultMsg;
import com.tawe.service.edu.entity.EduTeacher;
import com.tawe.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-10-29
 */
@Api("讲师管理")
@RestController
@RequestMapping("/edu/edu-teacher")
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("查询所有讲师信息")
    @GetMapping("select-all")
    public ResultEntity<List<EduTeacher>> selectAll() {


        List<EduTeacher> teachers = eduTeacherService.selectAll();
        if (teachers.isEmpty()) {
            return ResultEntity.error();
        } else {
            return ResultEntity.<List<EduTeacher>>ok().data("items", teachers);
        }
    }

    @ApiOperation("根据 ID 查询讲师信息")
    @GetMapping("select-one")
    public ResultEntity<EduTeacher> selectOne(@RequestParam("id")String id) {

        EduTeacher eduTeacher = eduTeacherService.selectOne(id);
        if (eduTeacher == null) {
            return ResultEntity.<EduTeacher>error().message(ResultMsg.NO_DATA);
        } else {
            // return ResultEntity.<EduTeacher>ok().data("item", eduTeacher);
            return ResultEntity.ok("eduTeacher", eduTeacher);
        }
    }

    @ApiOperation("根据 ID 删除讲师")
    @DeleteMapping("delete/{id}")
    public ResultEntity deleteById(@PathVariable("id") String id) {
        boolean status = eduTeacherService.deleteById(id);
        if (status) {
            return ResultEntity.ok();
        } else
        {
            return ResultEntity.error();
        }
    }
}

