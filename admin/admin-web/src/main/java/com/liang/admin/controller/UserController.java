package com.liang.admin.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.liang.admin.pojo.JsonResult;
import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liang on 2017/4/29.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/tolist.do")
    public String toList(){
        return "/user/list";
    }

    @RequestMapping(value = "/list.do",method = RequestMethod.GET)
    @ResponseBody
    public String list(User userEntity,
                             @RequestParam(value="pageNumber",defaultValue = "1") Integer pageNumber,
                             @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize){
        JsonResult jsonResult=new JsonResult();
        try {
            PageInfo<User> pageInfo=userService.list(userEntity,pageNumber,pageSize);
            jsonResult.setStatus(200);
            jsonResult.setData(pageInfo);
            jsonResult.setMsg("查询成功");
        }catch (Exception e){
            jsonResult.setStatus(500);
            jsonResult.setMsg(e.getStackTrace().toString());
        }

        return JSON.toJSONString(jsonResult);
    }

    @ApiOperation(notes="查找用户",value="按id查找用户",httpMethod="GET")
    @ApiImplicitParam(name = "id", value = "用户的id", required = true, dataType = "String")
    @RequestMapping(value = "/{id}.do",method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(@PathVariable("id") String id){
        User userEntity=userService.getUserById(id);
        String userStr= JSON.toJSONString(userEntity);
        return userStr;
    }

}
