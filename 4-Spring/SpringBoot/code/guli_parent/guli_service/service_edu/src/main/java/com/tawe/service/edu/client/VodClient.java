package com.tawe.service.edu.client;

import com.tawe.common.utils.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName VodClient
 * @Description TODO
 * @Author davidt
 * @Date 12/21/2020 4:48 PM
 * @Version 1.0
 **/
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @DeleteMapping("/vod/video/{videoId}")
    ResultEntity removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vod/video/delete-batch")
    ResultEntity removeVideoList(@RequestParam("videoIdList")List<String> videoIdList);
}
