package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.syh.dao.RoleDao;
import com.syh.dao.UserDao;
import com.syh.domain.Role;
import com.syh.domain.RolePermission;
import com.syh.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 角色业务类
 * @author: 沈煜辉
 * @create: 2020-03-26 14:15
 **/
@Service
public class RoleServiceImpl {

    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;

    public List<Role> findAll(Integer pageNum, Integer pageSize,String uid) {
        if (pageNum==null||pageNum<1){
            pageNum = 1;
        }
        if (pageSize==null||pageSize<10){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleDao.findAll();
        if (StringUtil.isNotEmpty(uid)){
            for (Role role : list) {
                String roleId = role.getId();
                UserRole userRole = userDao.findByUserIdAndRoleId(uid, roleId);
                if (userRole!=null){
                    role.setFlag(true);
                }else{
                    role.setFlag(false);
                }
            }
        }
        return list;
    }

    public String addPermission(String rid, String pid) {
        RolePermission rolePermission = roleDao.findByPermissionIdAndRoleId(rid,pid);
        int result = 0;
        if (rolePermission==null){
            RolePermission RolePermissionSave = new RolePermission();
            RolePermissionSave.setRoleId(rid);
            RolePermissionSave.setPermissionId(pid);
            result = roleDao.addPermission(RolePermissionSave);
        }else{
            return "2";
        }
        if (result>0){
            return "1";
        }
        return "0";
    }


    public String delPermission(String rid, String pid) {
        if (StringUtil.isEmpty(pid)||StringUtil.isEmpty(rid)){
            return "0";
        }
        RolePermission rolePermission = roleDao.findByRoleIdAndPreId(rid, pid);
        if (rolePermission!=null){
            int result = roleDao.delUserRole(rid,pid);
            if (result>0){
                return "1";
            }
            return "2";
        }
        return "2";
    }
}
