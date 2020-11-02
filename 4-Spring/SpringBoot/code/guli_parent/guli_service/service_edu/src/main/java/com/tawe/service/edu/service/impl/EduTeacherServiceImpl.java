package com.tawe.service.edu.service.impl;

import com.tawe.service.edu.entity.EduTeacher;
import com.tawe.service.edu.mapper.EduTeacherMapper;
import com.tawe.service.edu.service.EduTeacherService;
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
