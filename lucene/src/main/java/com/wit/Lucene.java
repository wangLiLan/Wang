package com.wit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wit.mapper")
public class Lucene {
    public static void main(String[] args) {
        SpringApplication.run(Lucene.class,args);
    }
}
