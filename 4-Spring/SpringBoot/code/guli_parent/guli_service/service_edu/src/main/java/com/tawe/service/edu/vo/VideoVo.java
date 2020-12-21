package com.tawe.service.edu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName VideoVo
 * @Description TODO
 * @Author davidt
 * @Date 12/14/2020 3:52 PM
 * @Version 1.0
 **/
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String videoOriginalName;
    private Boolean free;
}
