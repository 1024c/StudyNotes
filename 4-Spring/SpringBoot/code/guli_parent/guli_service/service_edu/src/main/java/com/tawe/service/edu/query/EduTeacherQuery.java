package com.tawe.service.edu.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName EduTeacherQuery
 * @Description TeacherQuery 查询对象
 * @Author davidt
 * @Date 11/3/2020 11:02 AM
 * @Version 1.0
 **/
@Data
public class EduTeacherQuery implements Serializable {

    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
