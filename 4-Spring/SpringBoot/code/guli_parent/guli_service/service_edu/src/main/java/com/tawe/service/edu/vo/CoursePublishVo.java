package com.tawe.service.edu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CoursePublishVo
 * @Description TODO
 * @Author davidt
 * @Date 12/15/2020 11:40 AM
 * @Version 1.0
 **/

@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price; //只用于显示
}
