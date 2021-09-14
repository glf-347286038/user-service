package com.user.utils;

import lombok.Data;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gaolingfeng
 * @Date: 2021/9/14 21:51
 */
public class ExportCvs {
    public static void main(String[] args) {
        List<Person> aList = new ArrayList<>();
        Person a;
        boolean writerTime = true;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 10000; i++) {
                a = new Person();
                a.setOrderNo(i + ",D/年,");
                a.setAge("age" + i);
                aList.add(a);
            }
            String[] titles = new String[]{"orderNo", "age", "name"};
            // 字段名称
            String[] properties = new String[]{"orderNo", "age", "name"};
            exportCsv(titles, writerTime, properties, aList);
            writerTime = false;
            aList.clear();
        }

        // 读取
        List<String> list = readCsv(new File("D:\\桌面\\新建文件夹\\test.csv"));
        Person person = new Person();
        String orderNo = list.get(1).split(",")[1];
        System.out.println(list.get(1));
        System.out.println(orderNo);
        person.setOrderNo(orderNo);
        System.out.println();

    }


    /**
     * 导出生成csv格式的文件
     *
     * @param titles     csv格式头文
     * @param properties 需要导出的数据实体的属性，注意与title一一对应
     * @param list       需要导出的对象集合
     * @author ccg
     */
    public static <T> void exportCsv(String[] titles, boolean writerTime, String[] properties, List<T> list) {
        File file = new File("D:\\桌面\\新建文件夹\\test.csv");
        //构建输出流，同时指定编码UTF-8
        OutputStreamWriter ow = null;
        try {
            ow = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
            //csv文件是逗号分隔,除第一个外,每次写入一个单元格数据后需要输入逗号
            // for循环时,只有第一次写入头行
            if (writerTime) {
                for (String title : titles) {
                    ow.write(title);
                    ow.write(",");
                }
                // 写完文件头后换行
                ow.write("\r\n");
            }
            // 写内容
            for (Object obj : list) {
                //利用反射获取所有字段
                Field[] fields = obj.getClass().getDeclaredFields();
                for (String property : properties) {
                    for (Field field : fields) {
                        //设置字段可见性
                        field.setAccessible(true);
                        if (property.equals(field.getName())) {
                            Object value = field.get(obj);
                            // 如果有,直接替换为空字符串
                            ow.write(value == null ? "" : String.valueOf(value).replace(",", ""));
                            ow.write(",");
                        }
                    }
                }
                //写完一行换行
                ow.write("\r\n");
                ow.flush();
            }

        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ow != null) {
                    ow.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取
     *
     * @param file csv文件(路径+文件)
     * @return 结果
     */
    public static List<String> readCsv(File file) {
        List<String> dataList = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception ignored) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }


}

@Data
class Person {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 年龄
     */
    private String age;
    /**
     * 姓名
     */
    private String name;
}
