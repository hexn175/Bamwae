package com.bluemsun.utils;

import java.text.MessageFormat;

public class WxUtil {
    //AppID(小程序ID)
    private final static String APP_ID = "wx631272a1f266ed63";
    //AppSecret(小程序密钥)
    private final static String APP_SECRET = "00f867c92609553c4e136d94a2ade31c";
    private final static String WX_LOGIN_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
    public static String getWxServerUrl(String code) {
        return MessageFormat.format(WX_LOGIN_SERVER_URL, new String[]{APP_ID, APP_SECRET, code});
    }

}
