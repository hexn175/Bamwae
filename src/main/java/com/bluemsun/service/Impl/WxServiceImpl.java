package com.bluemsun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.bluemsun.entity.WxUserInfo;
import com.bluemsun.mapper.WxUserMapper;
import com.bluemsun.service.IWxService;
import com.bluemsun.util.HttpClientUtils;
import com.bluemsun.util.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mac
 * @date 2022/5/23 - 20:56
 */
@Service
public class WxServiceImpl implements IWxService {
    @Autowired
    WxUserMapper wxUserMapper;
    @Override
    public WxUserInfo getLoginCertificate(String code) throws Exception {
        //请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
        // 发送请求
        String response = HttpClientUtils.getRequest(requestUrl);
        //格式化JSON数据
        WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
        //检查数据库中是否存在 OPENID
        WxUserInfo wxUserInfo_ = this.wxUserMapper.selectById(wxUserInfo.getOpenId());
        if (wxUserInfo_ == null) {
            //数据库中没有用户的 OPENID，添加到数据库中
            this.wxUserMapper.insert(wxUserInfo);
        } else {
            if (!wxUserInfo.getSessionKey().equals(wxUserInfo_.getSessionKey())) {
                //如果数据库保存的session_key和最新的session_key 不相同，则更新
                wxUserInfo_.setSessionKey(wxUserInfo.getSessionKey());
                this.wxUserMapper.updateById(wxUserInfo_);
            }
        }
        return wxUserInfo_;
    }
}
