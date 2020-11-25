package com.jachs.containertomcat.vo;

import java.util.List;

import lombok.Data;

/**
 * @author zhanchaohan
 * 
 */
@Data
public class UserVo {
    private String UserName;
    private int UserAge;
    private List<String>UserFriend;
    
}
