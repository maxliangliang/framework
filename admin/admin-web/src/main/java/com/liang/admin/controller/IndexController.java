package com.liang.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liang on 2017/6/5.
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("toIndex.do")
    public String toIndex(){
        return "index";
    }
}
