package com.user.login.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 * 国籍
 *
 * @Author: gaolingfeng
 * @Date: 2021/9/12 10:31
 */
@Data
@NoArgsConstructor
public class GithubUserInfoDTO {
    /**
     * token
     */
    private String accessToken;
    /**
     * id
     */
    private Integer id;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * github账号
     */
    private String account;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 住址
     */
    private String location;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 签名
     */
    private String bio;
}
