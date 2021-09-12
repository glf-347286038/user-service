package com.user.login.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.user.common.pojo.ResponseData;
import com.user.login.pojo.GithubUserInfoDTO;
import com.user.login.pojo.LoginConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gaolingfeng
 * @Date: 2021-09-11 01:08
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Value(value = "${githubLogin.client_id}")
    private String clientId;
    @Value(value = "${githubLogin.client_secret}")
    private String clientSecret;

    @GetMapping("/githubLogin")
    @SuppressWarnings("all")
    public ResponseData<GithubUserInfoDTO> githubLogin(@RequestParam String code) throws JsonProcessingException {
        System.out.println(clientId + ":" + clientSecret);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(LoginConstant.Github.CLIENT_ID, clientId);
        jsonObject.put(LoginConstant.Github.CLIENT_SECRET, clientSecret);
        jsonObject.put(LoginConstant.Github.CODE, code);
        Map<String, String> resp = restTemplate.postForObject(LoginConstant.Github.ACCESS_TOKEN_URL, jsonObject, Map.class);
        String accessToken = resp.get(LoginConstant.Github.ACCESS_TOKEN);
        System.out.println("请求的token为:" + accessToken);

        // 用token获取用户信息
        GithubUserInfoDTO githubUserInfoDTO = new GithubUserInfoDTO();
        if (!StringUtils.isEmpty(accessToken)) {
            HttpHeaders httpheaders = new HttpHeaders();
            httpheaders.add(LoginConstant.Github.AUTHORIZATION, LoginConstant.Github.TOKEN + accessToken);
            HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
            ResponseEntity<Map> exchange = restTemplate.exchange(LoginConstant.Github.USER_INFO_URL, HttpMethod.GET, httpEntity, Map.class);
            Map<String, Object> userInfo = new HashMap<>(8);
            userInfo = exchange.getBody();
            System.out.println(userInfo.toString());
            githubUserInfoDTO.setAccessToken(accessToken);
            githubUserInfoDTO.setAccount(String.valueOf(userInfo.get(LoginConstant.Github.ACCOUNT)));
            githubUserInfoDTO.setName(String.valueOf(userInfo.get(LoginConstant.Github.NAME)));
            githubUserInfoDTO.setNationality(String.valueOf(userInfo.get(LoginConstant.Github.NATIONALITY)));
            githubUserInfoDTO.setLocation(String.valueOf(userInfo.get(LoginConstant.Github.LOCATION)));
            githubUserInfoDTO.setId(Integer.valueOf(String.valueOf(userInfo.get(LoginConstant.Github.ID))));
            githubUserInfoDTO.setBio(String.valueOf(userInfo.get(LoginConstant.Github.BIO)));
        }
        return new ResponseData<>().success(githubUserInfoDTO);
    }

}
