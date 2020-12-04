package com.tawe.common.service.base.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @ClassName CustomException
 * @Description TODO
 * @Author davidt
 * @Date 12/4/2020 2:12 PM
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class CustomException extends RuntimeException {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("异常信息")
    private String msg;
}
