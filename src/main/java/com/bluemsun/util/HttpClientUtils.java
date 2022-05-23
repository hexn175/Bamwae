package com.bluemsun.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author mac
 * @date 2022/5/23 - 21:02
 */
public class HttpClientUtils {
    /**
     * GET请求
     */
    public static String getRequest(String url) throws Exception {
        //HttpClient对象
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        //跳过证书验证
        CloseableHttpClient closeableHttpClient = (CloseableHttpClient) SkipHttpsUtil.wrapClient();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = closeableHttpClient.execute(httpGet);
            //响应体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //格式化响应体
                return EntityUtils.toString(entity);
            }
        } catch (ClientProtocolException e) {
            throw  e;
        } catch (IOException e) {
            throw  e;
        } finally {
            response.close();
            closeableHttpClient.close();
        }
        return null;
    }
}
