package com.tawe.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultEntity
 * @Description 前后端统一接口
 * @Author davidt
 * @Date 11/3/2020 10:40 AM
 * @Version 1.0
 **/
@Getter
public class ResultEntity {
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<>();

    private ResultEntity(){}


    public static ResultEntity ok() {
        ResultEntity result = new ResultEntity();
        result.success = true;
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultMsg.SUCCESS;
        return result;
    }

    public static ResultEntity ok(String key, Object value) {
        ResultEntity result = new ResultEntity();
        result.success = true;
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultMsg.SUCCESS;
        result.data(key, value);
        return result;
    }

    public static ResultEntity error() {
        ResultEntity result = new ResultEntity();
        result.success = false;
        result.code = ResultCode.ERROR.getCode();
        result.message = ResultMsg.ERROR;
        return result;
    }

    public ResultEntity message(String message) {
        this.message = message;
        return this;
    }

    public ResultEntity data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultEntity data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResultEntity code(Integer code) {
        this.code = code;
        return this;
    }
}
