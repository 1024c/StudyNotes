package com.tawe.service.edu.client;

import com.tawe.common.utils.ResultEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName VodFileDegradeFeignClient
 * @Description TODO
 * @Author davidt
 * @Date 12/22/2020 4:59 PM
 * @Version 1.0
 **/

@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public ResultEntity removeVideo(String videoId) {
        return ResultEntity.error().message("time out");
    }

    @Override
    public ResultEntity removeVideoList(List<String> videoIdList) {
        return ResultEntity.error().message("time out");
    }
}
