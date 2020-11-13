package com.tawe.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tawe.service.edu.dto.SubjectDto;
import com.tawe.service.edu.entity.Subject;
import com.tawe.service.edu.entity.Teacher;
import com.tawe.service.edu.listener.SubjectReadListener;
import com.tawe.service.edu.mapper.SubjectMapper;
import com.tawe.service.edu.mapper.TeacherMapper;
import com.tawe.service.edu.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName SubjectServiceImpl
 * @Description TODO
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
            EasyExcelFactory.read(inputStream, SubjectDto.class, new SubjectReadListener(this)).sheet().doRead();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
