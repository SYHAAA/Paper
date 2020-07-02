package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户角色类
 * @author: 沈煜辉
 * @create: 2020-03-27 12:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {
    String permissionId;
    String roleId;
}
