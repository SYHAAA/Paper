package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Role;
import com.syh.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 角色controller
 * @author: 沈煜辉
 * @create: 2020-03-26 14:13
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("/findAll.do")
    public PageInfo<Role> findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize, String userId){
        List<Role> list = roleService.findAll(pageNum,pageSize,userId);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @RequestMapping("/addPermission.do")
    public String addPermission(String rid,String pid){
        return roleService.addPermission(rid,pid);
    }

    @RequestMapping("/delPermission.do")
    public String delPermission(String rid,String pid){
        return roleService.delPermission(rid,pid);
    }
}
