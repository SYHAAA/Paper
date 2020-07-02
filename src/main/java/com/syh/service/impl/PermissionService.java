package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.syh.dao.PermissionDao;
import com.syh.dao.RoleDao;
import com.syh.domain.Permission;
import com.syh.domain.Role;
import com.syh.domain.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 角色业务类
 * @author: 沈煜辉
 * @create: 2020-03-26 14:15
 **/
@Service
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;

    public List<Permission> findAll(Integer pageNum, Integer pageSize, String rid) {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if (pageSize==null||pageSize<10){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> list = permissionDao.findAll();
        if (StringUtil.isNotEmpty(rid)){
            for (Permission permission : list) {
                String permissionId = permission.getId();
                RolePermission rolePermission = roleDao.findByPermissionIdAndRoleId(rid, permissionId);
                if (rolePermission!=null){
                    permission.setFlag(true);
                }else{
                    permission.setFlag(false);
                }
            }
        }
        return list;
    }
}
