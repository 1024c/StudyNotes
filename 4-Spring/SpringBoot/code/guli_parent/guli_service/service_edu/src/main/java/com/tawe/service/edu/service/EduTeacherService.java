package com.tawe.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.service.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.query.EduTeacherQuery;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author tawe
 * @since 2020-10-29
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> selectAll();

    boolean deleteById(String id);

    void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery);
}
