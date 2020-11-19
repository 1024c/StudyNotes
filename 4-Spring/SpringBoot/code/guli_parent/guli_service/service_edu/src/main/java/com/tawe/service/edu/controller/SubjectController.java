package com.tawe.service.edu.controller;

import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.dto.SubjectTreeDto;
import com.tawe.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName SubjectController
 * @Description TODO
 * @Author davidt
 * @Date 11/13/2020 2:32 PM
 * @Version 1.0
 **/

@Api("课程分类管理")
@RestController
@RequestMapping("/edu/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("Excel 批量导入")
    @PostMapping("addSubject")
    public ResultEntity addSubjectFromExcel(
            @ApiParam("课程分类 Excel 文件")
            @RequestBody MultipartFile file) {

        subjectService.importSubjectData(file);

        return ResultEntity.ok();
    }

    @ApiOperation("获取课程分类树形结构")
    @GetMapping("getSubjectTree")
    public ResultEntity getSubjectTree() {
        List<SubjectTreeDto> subjects = subjectService.getSubjectTree();
        return ResultEntity.ok().data("items", subjects);
    }
}
