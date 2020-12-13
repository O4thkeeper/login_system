package com.example.login_system.controller;

import com.example.login_system.entity.User;
import com.example.login_system.service.UserService;
import com.example.login_system.utils.CodeUtil;
import com.example.login_system.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    /*登陆请求*/
    @PostMapping("/login")
    public String login(String username, String password, HttpServletRequest request) {
        if (!CodeUtil.checkVerifyCode(request)){
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("codeWrong",true);
            return "index";
        }
        if (userService.userExists(username)) {
            if (userService.userAuthenticate(username, password)) {
                request.getSession().setAttribute("username", username);
                if (username.equals("root")) return "redirect:users";
                return "redirect:welcome";
            }
            request.setAttribute("username",username);
            request.setAttribute("passwordWrong", true);
        } else {
            request.setAttribute("nameNotExist", true);
        }
        return "index";
    }

    /*修改密码、忘记密码手机验证后修改密码*/
    @PutMapping("/user")
    public String update(String username, String newPassword, HttpServletRequest request) {
        userService.updatePassword(username, newPassword);
        request.setAttribute("changed", true);
        return "welcome";
    }

    /*用户注册*/
    @PostMapping("/user")
    public String register(String username, String password, String phone,String idCode, HttpServletRequest request, Model model) {

        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("phone", phone);
        if (!redisUtil.checkValue(phone,idCode)){
            request.setAttribute("register", true);
            request.setAttribute("codeErr",true);
            model.addAttribute("register",true);
            return "index";
        }
        if (userService.userExists(username)) {
            request.setAttribute("register", true);
            request.setAttribute("nameExists", true);
            request.setAttribute("idCode",idCode);
            model.addAttribute("register",true);
            return "index";
        }
        userService.addUser(username, password, phone);
        request.getSession().setAttribute("username", username);
        return "welcome";
    }

    /*忘记密码，手机验证后修改密码*/
    @PostMapping("forget")
    public String forget(String phone,String idCode,HttpServletRequest request,Model model){
        if (!redisUtil.checkValue(phone,idCode)){
            request.setAttribute("codeErr",true);
            model.addAttribute("forget",true);
            return "index";
        }else return "change";
    }

    /*root用户获取所有用户信息*/
    @GetMapping("/users")
    public String findUsers(Model model) {
        List<User> allUsers = userService.findAllUsers();
        model.addAttribute("userList", allUsers);
        return "root";
    }

    /*root进行删除用户*/
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /*root获取修改用户的信息*/
    @GetMapping("/user/{id}")
    public String getUpdateUser(@PathVariable("id") int id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    /*root修改用户的信息*/
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable("id") int id,String password,String phone){
        userService.rootUpdateUser(id, password, phone);
        return "redirect:/users";
    }

}
