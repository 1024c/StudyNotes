package com.tawe.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.query.TeacherQuery;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author tawe
 * @since 2020-11-12
 */
public interface TeacherService extends IService<Teacher> {

    List<Teacher> selectAll();

    boolean deleteById(String id);

    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);
}
