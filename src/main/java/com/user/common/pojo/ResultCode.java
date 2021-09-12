package com.user.common.pojo;

import lombok.Getter;

/**
 * 状态码
 * @author gaolingfeng
 */
@Getter
public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(200, "success"),

    UNAUTHORIZED(401, "Unauthorized"),

    /* 失败状态码 */
    FAIL(1000, "请求异常");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
