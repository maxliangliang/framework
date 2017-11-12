package com.liang.admin.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.liang.admin.pojo.sys.user.User;
import com.liang.admin.service.ILoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by liang on 2017/5/2.
 */
@Controller
public class LoginController {

    private Logger logger=Logger.getLogger(LoginController.class);

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login.do")
    public String toLogin(){

        return "login";
    }

    @RequestMapping("/toLogin.do")
    @ResponseBody
    public String login(User userEntity, String captcha, Model model, HttpServletRequest request){

        if(StringUtils.isBlank(userEntity.getName()) || StringUtils.isBlank(userEntity.getPassword()) || StringUtils.isBlank(captcha)){

            model.addAttribute("msg","请输入信息");
            model.addAttribute("status","error");

            return JSON.toJSONString(model);
        }

        if(!captcha.equalsIgnoreCase((String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){

            model.addAttribute("msg","验证码错误");
            model.addAttribute("status","error");

            return JSON.toJSONString(model);
        }

        User user=loginService.login(userEntity.getName(),userEntity.getPassword());

        if(null != user){
            request.getSession().setAttribute("user",user);
            logger.info(user.getName()+" 登录成功");

            model.addAttribute("status","ok");
            model.addAttribute("url","/main.do");
            return JSON.toJSONString(model);
        }else{
            model.addAttribute("msg","用户名或密码错误");
            model.addAttribute("status","error");

            return JSON.toJSONString(model);
        }


    }

    /**
     * 打开主页面
     * @return
     */
    @RequestMapping("/main.do")
    public String main(){

        return "/main";
    }

    /**
     * 打开index页面，index是在main页面中嵌套的
     * @return
     */
    @RequestMapping("/index.do")
    public String index(){

        return "/index";
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/kaptchaImage.jpg")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //System.out.println("******************验证码是: " + code + "******************");

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        System.out.println("******************验证码是: " + capText + "******************");
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();

        return "redirect:/login.do";
    }

}
