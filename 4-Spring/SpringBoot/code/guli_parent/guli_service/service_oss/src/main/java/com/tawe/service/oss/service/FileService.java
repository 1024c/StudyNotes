package com.tawe.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileService
 * @Description TODO
 * @Author davidt
 * @Date 11/12/2020 3:44 PM
 * @Version 1.0
 **/
public interface FileService {
    String upload(MultipartFile file);

    String upload(MultipartFile file, String path);
}
