package com.tawe.service.edu.controller;

import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
