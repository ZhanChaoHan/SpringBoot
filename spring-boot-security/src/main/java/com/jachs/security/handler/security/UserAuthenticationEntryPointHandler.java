package com.jachs.security.handler.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用户未登录处理类
 * @author zhanchaohan
 * 
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint{
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public void commence ( HttpServletRequest request , HttpServletResponse response ,
            AuthenticationException authException ) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 401);
        map.put("msg", "未登录");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

}
