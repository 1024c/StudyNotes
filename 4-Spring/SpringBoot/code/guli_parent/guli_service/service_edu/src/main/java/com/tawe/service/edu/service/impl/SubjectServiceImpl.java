package com.tawe.service.edu.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tawe.service.edu.dto.SubjectExcelDto;
import com.tawe.service.edu.dto.SubjectTreeDto;
import com.tawe.service.edu.entity.Subject;
import com.tawe.service.edu.listener.SubjectReadListener;
import com.tawe.service.edu.mapper.SubjectMapper;
import com.tawe.service.edu.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SubjectServiceImpl
 * @Description Subject Service Impl
 * @Author davidt
 * @Date 11/13/2020 2:37 PM
 * @Version 1.0
 **/
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>  implements SubjectService {
    @Override
    public void importSubjectData(MultipartFile file) {
        // 1. 获取文件输入流
        try(InputStream inputStream = file.getInputStream()) {
            // 2. 使用 EasyExcel 进行读取
            EasyExcelFactory.read(inputStream, SubjectExcelDto.class, new SubjectReadListener(this)).sheet().doRead();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public List<SubjectTreeDto> getSubjectTree() {

        // 1. 获取所有 subject 记录
        List<Subject> subjects = this.list(null);
        // 2. 根据 ID 将记录放入 HashMap 中, 用于后续查找
        HashMap<String, SubjectTreeDto> subjectMap = new HashMap<>(subjects.size());
        subjects.forEach(subject -> {
            SubjectTreeDto subjectDto = new SubjectTreeDto();
            BeanUtils.copyProperties(subject, subjectDto);
            subjectMap.put(subject.getId(), subjectDto);
        });
        // 3. 根据 PID 从 HashMap 中取记录
        List<SubjectTreeDto> subjectTrees = new ArrayList<>();
        subjects.forEach(subject -> {
            // pid == 0 说明是 跟节点
            if ("0".equals(subject.getParentId())) {
                subjectTrees.add(subjectMap.get(subject.getId()));
            } else {
                // 二级节点
                subjectMap.get(subject.getParentId())
                        .getChildren().add(subjectMap.get(subject.getId()));
            }
        });
        System.out.println(subjectTrees);
        return subjectTrees;
    }
}
