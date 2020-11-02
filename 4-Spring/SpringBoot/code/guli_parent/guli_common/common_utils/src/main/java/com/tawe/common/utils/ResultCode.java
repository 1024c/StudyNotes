package com.tawe.common.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResultCode
 * @Description TODO
 * @Author davidt
 * @Date 11/2/2020 5:12 PM
 * @Version 1.0
 **/
public enum ResultCode {
    SUCCESS(20000),
    ERROR(40000);

    @Getter
    private Integer code;

    ResultCode(Integer code) {
        this.code = code;
    }
}
