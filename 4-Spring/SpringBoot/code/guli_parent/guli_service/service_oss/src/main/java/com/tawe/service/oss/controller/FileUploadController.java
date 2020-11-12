package com.tawe.service.oss.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.tawe.common.utils.ResultEntity;
import com.tawe.common.utils.ResultMsg;
import com.tawe.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileUploadController
 * @Description 阿里云文件上传
 * @Author davidt
 * @Date 11/12/2020 4:24 PM
 * @Version 1.0
 **/
@Api("阿里云文件管理")
@RestController
@RequestMapping("/oss/file/")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public ResultEntity upload(
            @ApiParam(name="file", value="文件", required = true)
            @RequestBody MultipartFile file
    ) {

        String uploadUrl = fileService.upload(file);
        return ResultEntity.ok().data("url", uploadUrl);
    }
}
