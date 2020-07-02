package com.syh.dao;

import com.syh.domain.Permission;
import com.syh.domain.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 权限dao
 * @author: 沈煜辉
 * @create: 2019-12-09 15:23
 **/
public interface PermissionDao {

    @Select("select * from PAPER_PERMISSION where id in (select permissionId from PAPER_ROLE_PERMISSION where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;


    /**
     * 查询所用权限
     * @return
     */
    @Select("select * from PAPER_PERMISSION")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url")
    })
    List<Permission> findAll();
}
