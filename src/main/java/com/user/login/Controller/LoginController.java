package com.user.login.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

//    // 授权码
//    public String getGithubAuthorizationCode(){
//
//    }


    @GetMapping("/githubLogin")
    @SuppressWarnings("all")
    public String githubLogin(@RequestParam String code) throws JsonProcessingException {
        System.out.println(clientId + ":" + clientSecret);
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", clientId);
        jsonObject.put("client_secret", clientSecret);
        jsonObject.put("code", code);
        Map<String, String> resp = restTemplate.postForObject("https://github.com/login/oauth/access_token", jsonObject, Map.class);
        System.out.println(resp);
        System.out.println("=====");
        HttpHeaders httpheaders = new HttpHeaders();
//        httpheaders.add("Authorization", "token " + resp.get("access_token"));
        HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
//        ResponseEntity<Map> exchange = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
//        System.out.println("exchange.getBody() = " + new ObjectMapper().writeValueAsString(exchange.getBody()));
        return resp.get("access_token");
    }

}
