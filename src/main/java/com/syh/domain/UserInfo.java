package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @description: 用户的实体类
 * @author: 沈煜辉
 * @create: 2019-12-09 10:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String id;
    private String email;
    private String userName;
    private String password;
    private String phoneNum;
    private String name;
    private Integer isAdmin;
    private Integer status;
    private Set<Role> roles;
}
