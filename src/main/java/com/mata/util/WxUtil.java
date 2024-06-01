package com.mata.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WxUtil {
    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    private final static String grantType = "authorization_code";

    /**
     *  返回openId
     */
    public String wxLogin(String code){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid",appid);
        paramMap.put("secret",secret);
        paramMap.put("js_code",code);
        paramMap.put("grant_type",grantType);
        String result= HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", paramMap);
        JSONObject jsonObject = JSONUtil.parseObj(result);
        try {
            return jsonObject.get("openid").toString();
        } catch (NullPointerException e){
            return null;
        }
    }
}
