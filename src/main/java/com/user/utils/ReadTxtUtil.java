package com.user.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 读取本地配置文件
 *
 * @Author: gaolingfeng
 * @Date: 2021/9/12 16:48
 */
@Slf4j
public class ReadTxtUtil {
    /**
     * key-value分割标志   开发环境
     */
    private static final String SPLIT_FLAG = "#=#";

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(8);
        String path = "D:\\桌面\\server\\myServer\\password.txt";
        try {
            map = getMap(path);
        } catch (IOException e) {
            log.error("文件读取失败{}", path);
        }
        System.out.println(map.toString());

    }

    public static Map<String, String> getMap(String path) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), StandardCharsets.UTF_8));
        String str;
        Map<String, String> map = new LinkedHashMap<>(8);
        String value = null, key;
        while ((str = br.readLine()) != null) {
            if (str.contains(SPLIT_FLAG)) {
                key = str.substring(0, str.indexOf(SPLIT_FLAG));
                value = str.substring(str.indexOf(SPLIT_FLAG) + 3);
                if (!StringUtils.isEmpty(value) && !StringUtils.isEmpty(key)) {
                    map.put(key, value);
                }
            }
            value += str;
        }
        return map;
    }

}
