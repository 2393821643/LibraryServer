package com.mata.interceptor;


import cn.hutool.core.util.StrUtil;

import cn.hutool.json.JSONUtil;
import com.mata.exception.BusinessException;
import com.mata.util.JwtUtil;
import com.mata.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1:获取请求体的token
        String token = request.getHeader("authorization");
        //1.1: 判断token是否为空
        if (StrUtil.isBlank(token)) {
            throw new BusinessException("登录失败");
        }
        // 解析token 获取id
        Integer id = jwtUtil.parseToken(token);
        if (id == null){
            throw new BusinessException("登录失败");
        }
        UserHolder.saveUser(id);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
