package com.zmm.redis.service.impl;

import com.zmm.redis.model.User;
import com.zmm.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/4/16
 * Email:65489469@qq.com
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    @Override
    public List<User> getAllUsers() {

        //字符串序列化格式 一般是key值字符串 value值不需要改动
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        List<User> userList = (List<User>) redisTemplate.opsForValue().get("userList");

        if(userList == null){

            //这个目的，是防止一瞬间10000个请求过来时，重复请求数据库
            synchronized (this){

                //从redis获取一下
                userList = (List<User>) redisTemplate.opsForValue().get("userList");

                if(userList == null){
                    System.out.println("第一次,数据库查询");

                    userList = new ArrayList<>();

                    //这里我们模拟从数据库查询数据
                    for (int i = 0; i < 7; i++) {
                        User user = new User(i,"name_"+i,"1376450336"+i);

                        userList.add(user);
                    }

                    redisTemplate.opsForValue().set("userList",userList);

                }else {
                    System.out.println("查询01，Redis缓存查询~~");
                }
            }

        }else {
            System.out.println("查询02，Redis缓存查询~");

        }

        return userList;
    }

    @Override
    public User getUser() {

        User user = (User) redisTemplate.opsForValue().get("user");

        return user;
    }

    @Override
    public void addUser(Integer id,String name,String phone) {
        //字符串序列化格式 一般是key值字符串 value值不需要改动
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        User user = new User(id,name,phone);

        redisTemplate.opsForValue().set("user",user);

    }

    @Override
    public void setAllUsers() {

        //字符串序列化格式 一般是key值字符串 value值不需要改动
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        List<User> userList = new ArrayList<>();

        //这里我们模拟从数据库查询数据
        for (int i = 23; i < 29; i++) {

            User user = new User(i,"name_"+i,"1376450336"+i);

            userList.add(user);
        }

        redisTemplate.opsForValue().set("userList",userList);

    }
}
