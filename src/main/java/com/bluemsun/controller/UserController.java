package com.bluemsun.controller;

import com.bluemsun.entity.WxUserInfo;
import com.bluemsun.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IWxService wxService;
    /***
     * 获取到微信用户的 OPENID
     */
    @RequestMapping("/login")
    public String login(@RequestParam("code") String code) throws Exception {
        WxUserInfo wxInfo = this.wxService.getLoginCertificate(code);
        //用户不存在，或者用户的信息不全
        return wxInfo==null || wxInfo.getNickName()==null?"0":"1";
    }
}
