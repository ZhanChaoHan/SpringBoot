package com.jachs.security.handler.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
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
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		writer.write("退出成功");
		writer.flush();
		writer.close();

	}

}