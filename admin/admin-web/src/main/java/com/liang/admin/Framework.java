package com.liang.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by neusoft on 2017/5/19.
 */
@SpringBootApplication
@ServletComponentScan   //这个就是扫描相应的Servlet包;
@MapperScan("com.liang.admin.dao")
public class Framework extends SpringBootServletInitializer {

    /**
     * 若打包成war包,则需要继承 org.springframework.boot.context.web.SpringBootServletInitializer类,
     * 覆盖其config(SpringApplicationBuilder)方法
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(Framework.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Framework.class, args);
    }
}

