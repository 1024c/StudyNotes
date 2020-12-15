package com.tawe.service.edu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author davidt
 * @Date 12/14/2020 3:51 PM
 * @Version 1.0
 **/

@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();

}
