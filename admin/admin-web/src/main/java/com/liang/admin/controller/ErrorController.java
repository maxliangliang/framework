package com.liang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误跳转界面
 * Created by liang on 2017/6/10.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/{error_code}.do")
    public String noAuth(@PathVariable("error_code") int error_code, Model model){

        switch (error_code){
            case 403:
                model.addAttribute("error_code",error_code);
                model.addAttribute("msg","您还未登录，或者已经登录超时");
                break;

            case 404:
                model.addAttribute("error_code",error_code);
                model.addAttribute("msg","页面已丢失");
                break;
            default:
        }

        return "/error";
    }
}
