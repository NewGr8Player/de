package com.sxkj.de;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeApplicationTests {

    @Test
    public void contextLoads() {

    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String taskUrl = "http://mobile.weather.com.cn/data/forecast/101010100.html";
        String result = restTemplate.getForObject(taskUrl, String.class);
        System.out.println(result);
    }
}
