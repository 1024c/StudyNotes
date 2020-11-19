package com.tawe.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tawe.service.edu.dto.SubjectTreeDto;
import com.tawe.service.edu.entity.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName SubjectService
 * @Description Subject Service Interface
 * @Author davidt
 * @Date 11/13/2020 2:34 PM
 * @Version 1.0
 **/
public interface SubjectService extends IService<Subject> {
    void importSubjectData(MultipartFile file);

    List<SubjectTreeDto> getSubjectTree();
}
