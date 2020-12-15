package com.tawe.service.edu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.entity.Chapter;
import com.tawe.service.edu.service.ChapterService;
import com.tawe.service.edu.vo.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-12-03
 */
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("nested-list/{courseId}")
    public ResultEntity listByCourseId(
            @PathVariable String courseId
    ) {
        List<ChapterVo> chapterVos = chapterService.nestedList(courseId);
        return ResultEntity.ok().data("items", chapterVos);
    }

    @PostMapping("save")
    public ResultEntity save(
            @RequestBody Chapter chapter
    ) {
        chapterService.save(chapter);
        return ResultEntity.ok();
    }

    @GetMapping("{id}")
    public ResultEntity getById(@PathVariable String id) {
        Chapter chapter = chapterService.getById(id);
        return ResultEntity.ok().data("item", chapter);
    }

    @PutMapping("{id}")
    public ResultEntity updateById(
            @PathVariable String id,
            @RequestBody Chapter chapter
    ) {
        chapter.setId(id);
        chapterService.updateById(chapter);
        return ResultEntity.ok();
    }

    @DeleteMapping("{id}")
    public ResultEntity removeById(@PathVariable String id) {
        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return ResultEntity.ok();
        } else {
            return ResultEntity.error().message("删除失败");
        }
    }

}

