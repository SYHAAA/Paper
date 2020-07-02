package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @description: 角色实体类
 * @author: 沈煜辉
 * @create: 2019-12-09 15:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private String id;
    private String roleName;
    private String roleDesc;
    private Set<Permission> permissions;
    private boolean flag;
}
