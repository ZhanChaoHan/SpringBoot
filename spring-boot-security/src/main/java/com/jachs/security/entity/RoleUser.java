package com.jachs.security.entity;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

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
public class RoleUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long Id;
    private String Name;
    private String Phone;
    private boolean Enabled;
    private String Username;
    private String Password;
    private Set<SecurityUser> Authorities;
    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
}
