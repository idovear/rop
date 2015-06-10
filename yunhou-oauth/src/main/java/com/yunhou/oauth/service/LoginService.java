package com.yunhou.oauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.yunhou.openapi.common.util.httpclient.HttpclientUtil;

/**
 * 
 * 登录服务<br/>
 * 
 * @author 何冰(hebing@bubugao.com)
 * @date: 2015年6月10日 上午11:29:49
 * @version 1.0
 * @since JDK 1.7
 */
public class LoginService {

    public static String login(String username, String password, String code) throws Exception {
        HttpclientUtil client = HttpclientUtil.create(0);
        try {
            String seq = "D90B68D753F571AE6D4FCDBD311E6AEC";
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("loginName", username));
            nvps.add(new BasicNameValuePair("password", password));
            nvps.add(new BasicNameValuePair("captchaCode", ""));
            nvps.add(new BasicNameValuePair("seq", seq));
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Referer", "https://ssl.yunhou.com");
            // ----------------------------登录 --------------------------
            String html = client.doPost("https://ssl.yunhou.com/bubugao-passport/login", headerMap, new UrlEncodedFormEntity(
                    nvps, "utf-8"), "utf-8");
            System.out.println(html);
            JSONObject json = new JSONObject(html);
            String url = "https://login.bubugao.com/bubugao-passport/sso?callback=Request.JSONP.request_map.request_1&token="
                    + json.getString("token") + "&seq=" + seq;
            html = client.doGet(url, "");
            System.out.println(html);
        } finally {
            client.shutdown();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String username = "18570641976";
        String password = "hb9413563214";
        login(username, password, "");
    }
}
