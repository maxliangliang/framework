package com.liang.admin.controller;

import com.alibaba.fastjson.JSON;
import com.liang.admin.pojo.JsonResult;
import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.service.IKeywordService;
import com.liang.admin.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by liang on 2017/4/30.
 */
//@RestController 由于这个controller中有restful的接口，也有正常的mvc的接口，所以这里把这个功能注释掉，按正常的mvc的写法
@Controller
@RequestMapping("/wordfilter")
public class KeywordController {

    private Logger logger=Logger.getLogger(KeywordController.class);

    @Autowired
    private IKeywordService keywordService;

    @Autowired
    private IUserService userService;

    /**
     * 查找关键词出现的次数
     *这个是个rest接口，必须传入正确的用户名，密码，验证通过了之后才能调用
     * @return
     */
    @ApiOperation(notes="关键词统计",value="检查用户传的内容中每个关键词出现的次数",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户的id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户的密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户的token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "text", value = "用户要检查的内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "commonKeyword", value = "是否需要开启公共过滤词", required = false, dataType = "boolean")
    })
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public String count(String username,String password,String token, String text,Boolean commonKeyword){
        JsonResult jsonResult=new JsonResult();

        User userEntity=new User();
        userEntity.setName(username);
        userEntity.setPassword(password);
        userEntity.setToken(token);

        //检查用户是否存在
        User user=userService.checkUser(userEntity);

        String userId=user.getId();

        //如果用户没有指定，则默认使用公用关键词
        if(commonKeyword==null){
            commonKeyword=true;
        }


        try {
            Map<String,Integer> data=keywordService.count(userId,text,commonKeyword);
            if(data!=null){
                jsonResult.setStatus(200);
                jsonResult.setData(data);
                jsonResult.setResultCount(data.size());
                jsonResult.setMsg("success");
            }
        }catch (Exception e){
            logger.error(e);

            jsonResult.setStatus(500);
            jsonResult.setData(null);
            jsonResult.setResultCount(0);
            jsonResult.setMsg("error");
        }

        return JSON.toJSON(jsonResult).toString();
    }


    /**
     * 替换关键词
     *这个是个rest接口，必须传入正确的用户名，密码，验证通过了之后才能调用
     * @return
     */
    @ApiOperation(notes="关键词替换",value="替换用户传的内容中每个关键词为*",httpMethod="POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户的id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户的密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户的token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "text", value = "用户要检查的内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "commonKeyword", value = "是否需要开启公共过滤词", dataType = "boolean")
    })
    @RequestMapping(value = "/replaceText",method = RequestMethod.POST)
    @ResponseBody
    public String replaceText(String username,String password,String token, String text,Boolean commonKeyword){

        JsonResult jsonResult=new JsonResult();

        User userEntity=new User();
        userEntity.setName(username);
        userEntity.setPassword(password);
        userEntity.setToken(token);

        User user=userService.checkUser(userEntity);

        String userId=user.getId();

        //如果用户没有指定，则默认使用公用关键词
        if(commonKeyword==null){
            commonKeyword=true;
        }

        try {
            String data=keywordService.replaceText(userId,text,commonKeyword);

            if(!StringUtils.isBlank(data)){
                jsonResult.setStatus(200);
                jsonResult.setData(data);
                jsonResult.setResultCount(0);
                jsonResult.setMsg("success");
            }
        }catch (Exception e){
            logger.error(e);

            jsonResult.setStatus(500);
            jsonResult.setData(null);
            jsonResult.setResultCount(0);
            jsonResult.setMsg("error");
        }

        return JSON.toJSON(jsonResult).toString();
    }




}
