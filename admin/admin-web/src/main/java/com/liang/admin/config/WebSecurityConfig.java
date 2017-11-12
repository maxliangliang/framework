package com.liang.admin.config;//package com.liang.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.header.HeaderWriter;
//import org.springframework.security.web.header.HeaderWriterFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by liang on 2017/5/13.
// */
//@Configuration
//@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled=true)//开启@PreAuthorize注解
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        /**
//         * 自定义HeaderWriter，用以覆盖security默认的Header,
//         * 使默认的"X-Frame-Options：DENY"禁止一切iframe调用
//         * 转化为"X-Frame-Options：SAMEORIGIN"允许同域下的iframe调用
//         */
//        HeaderWriter headerWriter = new HeaderWriter() {
//            @Override
//            public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
//                response.setHeader("X-Frame-Options","SAMEORIGIN");
//            }
//        };
//        List<HeaderWriter> headerWriters = new ArrayList<>();
//        headerWriters.add(headerWriter);
//        HeaderWriterFilter headerWriterFilter = new HeaderWriterFilter(headerWriters);
//        http.addFilter(headerWriterFilter);
//
//    }
//
//}
