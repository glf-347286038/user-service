package com.user.login.pojo;

/**
 * 登录常量
 *
 * @author gaolingfeng
 */
public interface LoginConstant {
    /**
     * github相关常量
     */
    interface Github {
        /**
         * 应用id
         */
        String CLIENT_ID = "client_id";
        /**
         * 应用密钥
         */
        String CLIENT_SECRET = "client_secret";
        /**
         * 授权码
         */
        String CODE = "code";
        /**
         * token
         */
        String ACCESS_TOKEN = "access_token";
        /**
         * token
         */
        String TOKEN = "token ";
        /**
         * 请求token的地址
         */
        String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
        /**
         * 获取用户信息的url
         */
        String USER_INFO_URL = "https://api.github.com/user";
        /**
         * Authorization
         */
        String AUTHORIZATION = "Authorization";
        /**
         * 账号
         */
        String ACCOUNT = "login";
        /**
         * id
         */
        String ID = "id";
        /**
         * 用户姓名
         */
        String NAME = "name";
        /**
         * 国籍
         */
        String NATIONALITY = "company";
        /**
         * 地址
         */
        String LOCATION = "location";
        /**
         * 签名
         */
        String BIO = "bio";

    }

}
