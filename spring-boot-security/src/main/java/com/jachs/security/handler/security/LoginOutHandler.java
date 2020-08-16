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
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 登出处理handler
 * 
 * @author zhanchaohan
 */
@Component
public class LoginOutHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("code", "200");
        paramMap.put("message", "注销成功!");
        paramMap.put("security", SecurityContextHolder.getContext().getAuthentication());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(paramMap));
        out.flush();
        out.close();

	}

}