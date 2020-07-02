package com.syh.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description: 权限实体类
 * @author: 沈煜辉
 * @create: 2019-12-09 15:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private String id;
    private String permissionName;
    private String url;
    private boolean flag;
}
