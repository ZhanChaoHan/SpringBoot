package com.jachs.security.entity;


import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhanchaohan
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser implements GrantedAuthority{
    private static final long serialVersionUID = 1L;
    private Integer Code;
    private String Authority;

}
