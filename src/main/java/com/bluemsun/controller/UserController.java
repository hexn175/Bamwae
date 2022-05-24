package com.bluemsun.controller;

import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User login(String code) throws Exception {
        User user = userService.login(code);
        return user;
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



        //测试
    }
}
