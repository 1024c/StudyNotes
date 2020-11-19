package com.tawe.service.edu.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubjectTreeDto
 * @Description 用于前端 Tree_node 显示
 * @Author davidt
 * @Date 11/16/2020 11:04 AM
 * @Version 1.0
 **/

@Data
public class SubjectTreeDto {
    private String id;
    private String title;

    private List<SubjectTreeDto> children = new ArrayList<>();
}
