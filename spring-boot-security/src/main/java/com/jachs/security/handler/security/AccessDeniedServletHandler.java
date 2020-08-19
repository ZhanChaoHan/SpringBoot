package com.jachs.security.handler.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 重写AccessDeniedHandler实现自定义状态码
 * @author zhanchaohan
 * 
 */
@Component
public class AccessDeniedServletHandler implements AccessDeniedHandler{
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle ( HttpServletRequest request , HttpServletResponse response ,
            AccessDeniedException accessDeniedException ) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", "403");
        map.put("msg", accessDeniedException.getMessage());
        map.put("data","");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

}
