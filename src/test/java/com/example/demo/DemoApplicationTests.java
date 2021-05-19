package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        File file = new File("src");
        System.out.println(file.getAbsolutePath() +  File.separator +  "main" + File.separator +  "resources" + File.separator +"maps");
    }

}
