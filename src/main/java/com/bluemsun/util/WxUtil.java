package com.bluemsun.util;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * @author mac
 * @date 2022/5/23 - 21:03
 */
public class WxUtil {
    private final static String APP_ID = "wx631272a1f266ed63";
    private final static String APP_SECRET = "00f867c92609553c4e136d94a2ade31c";
    //
    private final static String WX_LOGIN_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
    public static String getWxServerUrl(String code) throws IOException {
        String url = MessageFormat.format(WX_LOGIN_SERVER_URL, new String[]{APP_ID, APP_SECRET, code});
        return url;
    }
}
