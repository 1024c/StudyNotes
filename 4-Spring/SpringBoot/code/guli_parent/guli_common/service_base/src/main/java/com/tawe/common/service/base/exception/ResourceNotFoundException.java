package com.tawe.common.service.base.exception;

import com.tawe.common.utils.ResultCode;
import com.tawe.common.utils.ResultMsg;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @ClassName ResourceNotFoundException
 * @Description 自定义异常类 - 资源不存在
 * @Author davidt
 * @Date 11/3/2020 4:36 PM
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ResourceNotFoundException extends RuntimeException {

    @ApiModelProperty("状态码")
    private Integer code = ResultCode.ERROR.getCode();
    @ApiModelProperty("异常信息")
    private String msg = ResultMsg.ERROR;

}
