package com.jachs.swagger.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Description 用户实体
 **/
@Data
@Builder
public class User {
    private String username;
    private Integer age;
}
