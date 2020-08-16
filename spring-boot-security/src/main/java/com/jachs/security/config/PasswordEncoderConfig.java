package com.jachs.security.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/****
 * 密码加密
 * @author zhanchaohan
 *
 */
@Component
public class PasswordEncoderConfig implements PasswordEncoder {
	//默认提供加密对象
	public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
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
