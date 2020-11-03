package com.tawe.common.utils;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @Description ResultCode 状态码
 * @Author davidt
 * @Date 11/2/2020 5:12 PM
 * @Version 1.0
 **/
public enum ResultCode {
    SUCCESS(20000),
    ERROR(40000),
    NO_DATA(4004);

    @Getter
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }
}
