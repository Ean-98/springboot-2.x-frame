package cn.hlsd.websys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hlsd.websys.mapper")
public class WebsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsysApplication.class, args);
    }

}
