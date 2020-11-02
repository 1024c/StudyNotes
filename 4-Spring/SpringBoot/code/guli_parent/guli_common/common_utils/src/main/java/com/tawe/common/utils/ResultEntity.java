package com.tawe.common.utils;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultEntity
 * @Description TODO
 * @Author davidt
 * @Date 11/2/2020 5:14 PM
 * @Version 1.0
 **/

@Getter
public class ResultEntity<T> {

    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private Map<String, T> data;

    private ResultEntity(){}

    public static <E> ResultEntity<E> ok() {
        return ok("", null);
    }

    public static <E> ResultEntity<E> ok(String key, E value) {
        ResultEntity<E> result = new ResultEntity<>();
        result.success = true;
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultMsg.SUCCESS;
        Map<String, E> map = new HashMap<>(1);
        if (!key.isEmpty() && value != null) {
            map.put(key, value);
        }
        result.data = map;
        return result;
    }

    public static <E> ResultEntity<E> error() {
        ResultEntity<E> result = new ResultEntity<>();
        result.success = false;
        result.code = ResultCode.ERROR.getCode();
        result.message = ResultMsg.ERROR;
        result.data = new HashMap<>(0);
        return result;
    }

    public ResultEntity<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultEntity<T> data(String key, T value) {
        this.data.put(key, value);
        return this;
    }

    public ResultEntity<T> data(Map<String, T> data) {
        this.data = data;
        return this;
    }

}