package com.example.login_system.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.login_system.service.UserService;
import com.example.login_system.utils.CommonUtil;
import com.example.login_system.utils.MessageUtil;
import com.example.login_system.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IdCodeController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    @ResponseBody
    @PostMapping("code")
    public boolean getIdCode(String phone) throws ClientException {
        if (userService.findUserByPhone(phone)!=null) {
            String idCode = CommonUtil.getRandomNum(6);

            /*发短信*/
//            MessageUtil.sendSms(phone,idCode);

            redisUtil.setKey(phone, idCode, 5);
            return true;
        }
        return false;
    }

    @ResponseBody
    @PostMapping("regCode")
    public boolean getRegIdCode(String phone) throws ClientException {
        if (userService.findUserByPhone(phone)==null) {
            String idCode = CommonUtil.getRandomNum(6);

            /*发短信*/
//            MessageUtil.sendSms(phone,idCode);

            redisUtil.setKey(phone, idCode, 5);
            return true;
        }
        return false;
    }

    @GetMapping("code")
    public String checkIdCode(String phone, String idCode, HttpServletRequest request) {

        String res = redisUtil.getKey(phone);
        if (res ==null||!res.equals(idCode)){
            request.setAttribute("codeErr",true);
            return "index";
        }
        request.getSession().setAttribute("username",userService.findUserByPhone(phone).getUsername());

        return "redirect:welcome";
    }

}
