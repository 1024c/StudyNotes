package com.tawe.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tawe.edu.entity.EduTeacher;
import com.tawe.edu.mapper.EduTeacherMapper;
import com.tawe.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author tawe
 * @since 2020-10-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Resource
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public EduTeacher selectOne(String id) {
        return eduTeacherMapper.selectById(id);
    }

    @Override
    public List<EduTeacher> selectAll() {
        return eduTeacherMapper.selectList(null);
    }

    @Override
    public boolean deleteById(String id) {
        return eduTeacherMapper.deleteById(id) > 0;
    }
}
