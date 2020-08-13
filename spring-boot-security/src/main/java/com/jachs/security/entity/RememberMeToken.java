package com.jachs.security.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zhanchaohan
 * 
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
