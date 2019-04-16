package com.zmm.redis.controller;

import com.zmm.redis.model.User;
import com.zmm.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/4/3
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping("/addUser")
    public String addUser(@RequestParam("id")Integer id, @RequestParam("name")String name, @RequestParam("phone")String phone){

        userService.addUser(id,name,phone);

        return "添加成功";
    }

    @GetMapping("/setAllUsers")
    public String setAllUsers(){

        userService.setAllUsers();
        return "添加成功";
    }

    @GetMapping("/getAllUsers")
    public Object getAllUsers(){

        return userService.getAllUsers();
    }

    @GetMapping("/getUser")
    public Object getUser(){

        User user = userService.getUser();

        return user;
    }



}
