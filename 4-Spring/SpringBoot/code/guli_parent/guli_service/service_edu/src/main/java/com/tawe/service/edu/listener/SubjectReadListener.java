package com.tawe.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tawe.common.service.base.exception.ResourceNotFoundException;
import com.tawe.service.edu.vo.SubjectExcelVo;
import com.tawe.service.edu.entity.Subject;
import com.tawe.service.edu.service.SubjectService;

/**
 * @ClassName SubjectReadListener
 * @Description TODO
 * @Author davidt
 * @Date 11/13/2020 2:44 PM
 * @Version 1.0
 **/
public class SubjectReadListener extends AnalysisEventListener<SubjectExcelVo> {

    private final SubjectService subjectService;

    public SubjectReadListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @Override
    public void invoke(SubjectExcelVo subjectExcelVo, AnalysisContext analysisContext) {
        if (subjectExcelVo == null) {
            throw new ResourceNotFoundException();
        }
        // 添加 一级分类
        // 1. 检查 一级分类 是否已存在
        String firstLevel = subjectExcelVo.getFirstLevelSubject();
        Subject firstLevelSubject = checkSubject(firstLevel, "0");
        if (firstLevelSubject == null) {
            firstLevelSubject = insertSubject(firstLevel, "0");
        }

        // 设置 一级分类 pid
        String pid = firstLevelSubject.getId();

        // 添加 二级分类
        // 1. 检查 一级分类 是否已存在
        String secondLevel = subjectExcelVo.getSecondLevelSubject();
        Subject secondLevelSubject = checkSubject(secondLevel, pid);
        if (secondLevelSubject == null) {
            insertSubject(secondLevel, pid);
        }

    }

    private Subject insertSubject(String subjectName, String pid) {
        Subject subject = new Subject();
        subject.setTitle(subjectName);
        subject.setParentId(pid);
        subjectService.save(subject);
        return subject;
    }

    private Subject checkSubject(String subjectName, String pid) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", subjectName);
        queryWrapper.eq("parent_id", pid);
        return subjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) { // do after all saved

    }
}
