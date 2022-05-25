package com.bluemsun.controller;

import com.bluemsun.dto.UserDto;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    Logger logger = LoggerFactory.getLogger(getClass());


    @ResponseBody
    @PostMapping ( "/login")
    public Map login(@RequestBody UserDto userDto) throws Exception {
        Map map = new HashMap();
        Map resMap = userService.login(userDto.getCode());
        if (resMap.get("user") != null) {
            map.put("user", resMap.get("user"));
            map.put("token", resMap.get("token"));
            map.put("code",0);
            map.put("msg","登陆成功");

        } else {
            map.put("code", 1);
            map.put("msg", "登录失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "select", method = RequestMethod.GET)
    public User select(Integer id, Integer pn) {

//        分页查询对象：
//        pn:当前页 size:一页多少数据
//        Page<User> userPage  = new Page<>(pn, 2);
////        分页查询结果：
////        quarryWrapper 查询条件
//        Page<User> page = userService.page(userPage, null);
//        System.out.println("总页码："+page.getPages());
//        System.out.println("当前页："+page.getCurrent());
//        System.out.println("分页查询结果："+page.getRecords());
//        System.out.println("数据总数："+page.getTotal());
        return userService.selectUserById(id);
    }
}
