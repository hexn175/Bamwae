package com.bluemsun.service;

import com.bluemsun.entity.WxUserInfo;
import com.bluemsun.mapper.WxUserMapper;

/**
 * @author mac
 * @date 2022/5/23 - 21:52
 */
public interface IWxService {
    public WxUserInfo getLoginCertificate(String code) throws Exception;
}
