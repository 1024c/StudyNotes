package com.tawe.service.edu.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName CourseCategory
 * @Description 课程分类 - 用于 Excel 导入使用
 * @Author davidt
 * @Date 11/13/2020 2:30 PM
 * @Version 1.0
 **/

@Data
public class SubjectExcelDto {

    @ExcelProperty(index = 0)
    private String firstLevelSubject;
    @ExcelProperty(index = 1)
    private String secondLevelSubject;

}
