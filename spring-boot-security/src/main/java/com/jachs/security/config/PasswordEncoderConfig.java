package com.jachs.security.config;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/****
 * 密码加密
 * 
 * @author zhanchaohan
 * @see https://docs.spring.io/spring-security/site/docs/current/reference/html5/#authentication-password-storage
 */
@Component
public class PasswordEncoderConfig implements PasswordEncoder {
    //默认提供加密对象
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder ();
    }

    @Override
    public String encode ( CharSequence rawPassword ) {
        // 加密方法可以根据自己的需要修改
        
     // Create an encoder with strength 16
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
//        String result = encoder.encode(rawPassword);
        
     // Create an encoder with all the defaults
//        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
//        String result = encoder.encode("myPassword");
        
     // Create an encoder with all the defaults
//        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
//        String result = encoder.encode("myPassword");
        
     // Create an encoder with all the defaults
//        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
//        String result = encoder.encode("myPassword");
        
        return rawPassword.toString ();
    }

    @Override
    public boolean matches ( CharSequence rawPassword , String encodedPassword ) {
        return encode ( rawPassword ).equals ( encodedPassword );
    }

}
