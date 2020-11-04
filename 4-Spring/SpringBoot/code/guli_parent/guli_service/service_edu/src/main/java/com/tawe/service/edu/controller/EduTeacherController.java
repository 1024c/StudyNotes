package com.tawe.service.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.common.service.base.exception.ResourceNotFoundException;
import com.tawe.common.utils.ResultCode;
import com.tawe.common.utils.ResultEntity;
import com.tawe.common.utils.ResultMsg;
import com.tawe.service.edu.entity.EduTeacher;
import com.tawe.service.edu.query.EduTeacherQuery;
import com.tawe.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api("讲师管理")
@RestController
@RequestMapping("/edu/edu-teacher")
@CrossOrigin
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("新增讲师")
    @PostMapping("insert")
    public ResultEntity save(
            @ApiParam(name = "Teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher
    ) {
        boolean save = eduTeacherService.save(eduTeacher);
        return save ? ResultEntity.ok() : ResultEntity.error();
    }


    @ApiOperation("查询所有讲师信息")
    @GetMapping("select-all")
    public ResultEntity selectAll() {

        List<EduTeacher> teachers = eduTeacherService.selectAll();
        if (teachers.isEmpty()) {
            return ResultEntity.error();
        } else {
            return ResultEntity.ok().data("items", teachers);
        }
    }

    @ApiOperation("查询所有讲师信息 - 分页展示")
    @PostMapping("select-all-with-page")
    public ResultEntity selectAll(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @RequestParam(value = "page") Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @RequestParam(value = "limit") Integer limit,
            @ApiParam(name = "teacherQuery", value = "查询对象")
            @RequestBody EduTeacherQuery eduTeacherQuery
    ) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageQuery(pageParam, eduTeacherQuery);
        // eduTeacherService.page(pageParam, null);
        List<EduTeacher> teachers = pageParam.getRecords();
        long total = pageParam.getTotal();
        if (teachers.isEmpty()) {
            return ResultEntity.ok().message(ResultMsg.NO_DATA);
        } else {
            // return ResultEntity.ok().data("items", pageParam);
            return ResultEntity.ok().data("total", total).data("rows", teachers);
        }
    }

    @ApiOperation("根据 ID 查询讲师信息")
    @GetMapping("select-one")
    public ResultEntity selectOne(
            @ApiParam(name = "id", value = "讲师 ID", required = true)
            @RequestParam("id")String id) {

        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if (eduTeacher == null) {
            throw new ResourceNotFoundException(ResultCode.NO_DATA.getCode(), ResultMsg.NO_DATA);
            // return ResultEntity.error().message(ResultMsg.NO_DATA);
        } else {
            return ResultEntity.ok("eduTeacher", eduTeacher);
        }
    }

    @ApiOperation("根据 ID 修改讲师")
    @PostMapping("update")
    public ResultEntity updateById(
            // @ApiParam(name = "id", value = "讲师 ID", required = true)
            // @RequestParam("id") String id,
            @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher
    ) {
        // eduTeacher.setId(id);
        boolean result = eduTeacherService.updateById(eduTeacher);
        return result ? ResultEntity.ok() : ResultEntity.error();
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

