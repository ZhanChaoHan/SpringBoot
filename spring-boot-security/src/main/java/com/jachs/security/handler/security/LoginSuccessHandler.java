package com.jachs.security.handler.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 登陆成功处理handler
 * @author zhanchaohan
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        //登录成功返回
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", "400");
        paramMap.put("message", "登录成功!");
        paramMap.put("security", SecurityContextHolder.getContext().getAuthentication());
        //设置返回请求头
        response.setContentType("application/json;charset=utf-8");
        //写出流
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(paramMap));
        out.flush();
        out.close();
    }
    
}