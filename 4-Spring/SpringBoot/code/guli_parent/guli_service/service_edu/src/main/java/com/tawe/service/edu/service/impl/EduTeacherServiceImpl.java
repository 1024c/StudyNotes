package com.tawe.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.service.edu.entity.EduTeacher;
import com.tawe.service.edu.mapper.EduTeacherMapper;
import com.tawe.service.edu.query.EduTeacherQuery;
import com.tawe.service.edu.service.EduTeacherService;
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
 * @since 2020-10-29
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Resource
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public List<EduTeacher> selectAll() {
        return eduTeacherMapper.selectList(null);
    }

    @Override
    public boolean deleteById(String id) {
        return eduTeacherMapper.deleteById(id) > 0;
    }

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (eduTeacherQuery != null) {
            String name = eduTeacherQuery.getName();
            Integer level = eduTeacherQuery.getLevel();
            String begin = eduTeacherQuery.getBegin();
            String end = eduTeacherQuery.getEnd();

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
