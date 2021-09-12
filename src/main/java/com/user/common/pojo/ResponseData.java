package com.user.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回消息统一格式
 *
 * @Author: gaolingfeng
 * @Date: 2021/1/17 22:04
 */
@Data
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = 5434299964409715017L;
    /**
     * 是否成功,默认true
     */
    private Boolean success = true;
    /**
     * 返回码
     */
    private Integer code = 200;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public ResponseData(Integer code) {
        this.code = code;
    }

    public ResponseData() {

    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<T>(ResultCode.SUCCESS.getCode());
        responseData.setData(data);
        return responseData;
    }
}
