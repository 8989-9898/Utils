package com.gm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName: mybatis
 * @Package: comg.gm
 * @ClassName: StartApp
 * @Author: lin
 * @Description: springboot的启动类
 * @Date: 2019-04-22 9:57
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan({"com.gm.mapper"})
@EnableSwagger2
public class StartApp {
    public static void main(String[] args) {
        SpringApplication.run(StartApp.class,args);
    }
}
