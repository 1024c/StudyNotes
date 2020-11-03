package com.tawe.service.edu.handler;

import com.tawe.common.service.base.exception.ResourceNotFoundException;
import com.tawe.common.utils.ExceptionUtil;
import com.tawe.common.utils.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理类
 * @Author davidt
 * @Date 11/3/2020 4:32 PM
 * @Version 1.0
 **/

@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultEntity runTimeExceptionError(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        return ResultEntity.error();
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResultEntity resourceNotFoundExceptionError(ResourceNotFoundException e) {
        log.error(ExceptionUtil.getMessage(e));
        return ResultEntity.error().code(e.getCode()).message(e.getMsg());
    }

}
