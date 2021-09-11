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
        String CLIENT_ID = "13112e32b406a71b053a";
        /**
         * 应用密钥
         */
        String CLIENT_SECRET = "4b14c49b25226517d5379f7da3a9e98c9dd287e8";
        /**
         * github授权后回调地址
         */
        String CALLBACK = "http://localhost/callback";
        /**
         * 获取code的url
         */
        String CODE_URL = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&state=STATE&redirect_uri=" + CALLBACK + "";
        /**
         * 获取token的url
         */
        String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=CODE&redirect_uri=" + CALLBACK + "";
        /**
         * 获取用户信息的url
         */
        String USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";

    }

}
