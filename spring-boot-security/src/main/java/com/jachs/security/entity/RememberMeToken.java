package com.jachs.security.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 自定义持久化实体和
 * @author zhanchaohan
 * @see org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken
 * 和官方比改了个名字而已可以自己加字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RememberMeToken {
    private String series;
    private String loginName;
    private String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date lastUsed;
}
