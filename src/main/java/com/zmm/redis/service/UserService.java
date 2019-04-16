package com.zmm.redis.service;

import com.zmm.redis.model.User;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/3/27
 * Email:65489469@qq.com
 */
public interface UserService {

    List<User> getAllUsers();

    User getUser();

    void addUser(Integer id,String name,String phone);

    void setAllUsers();

}

