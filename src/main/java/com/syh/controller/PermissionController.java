package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Permission;
import com.syh.service.impl.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/findAll.do")
    public PageInfo<Permission> findAll(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer pageSize, String rid){
        List<Permission> list = permissionService.findAll(pageNum,pageSize,rid);
        PageInfo<Permission> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
