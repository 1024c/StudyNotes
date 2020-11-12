package com.tawe.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.service.edu.entity.Teacher;
import com.tawe.service.edu.mapper.TeacherMapper;
import com.tawe.service.edu.query.TeacherQuery;
import com.tawe.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author tawe
 * @since 2020-11-12
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> selectAll() {
        return teacherMapper.selectList(null);
    }

    @Override
    public boolean deleteById(String id) {
        return teacherMapper.deleteById(id) > 0;
    }

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (teacherQuery != null) {
            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String begin = teacherQuery.getBegin();
            String end = teacherQuery.getEnd();

            // if (name != null && !name.isEmpty()) {
            if (!StringUtils.isEmpty(name)) {
                queryWrapper.like("name", name);
            }

            if (!StringUtils.isEmpty(level)) {
                queryWrapper.eq("level", level);
            }

            if (!StringUtils.isEmpty(begin)) {
                queryWrapper.ge("gmt_create", begin);
            }

            if (!StringUtils.isEmpty(end)) {
                queryWrapper.le("gmt_create", end);
            }
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
