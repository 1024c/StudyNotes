package com.tawe.edu.service;

import com.tawe.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

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

    EduTeacher selectOne(String id);

    List<EduTeacher> selectAll();

    boolean deleteById(String id);
}
