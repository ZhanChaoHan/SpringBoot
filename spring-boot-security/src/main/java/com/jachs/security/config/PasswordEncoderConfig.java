package com.jachs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/****
 * 
 * @author zhanchaohan
 *
 */
@Component
public class PasswordEncoderConfig implements PasswordEncoder {
	//默认提供加密对象
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String encode(CharSequence rawPassword) {
		// 加密方法可以根据自己的需要修改
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encode(rawPassword).equals(encodedPassword);
	}

}
