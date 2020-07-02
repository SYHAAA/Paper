package com.syh.dao;

import com.github.pagehelper.util.StringUtil;
import com.syh.domain.Permission;
import com.syh.domain.Role;
import com.syh.domain.RolePermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 角色的dao
 * @author: 沈煜辉
 * @create: 2019-12-09 15:23
 **/
public interface RoleDao {

    /**
     * 根据用户id查询出所有对应的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from PAPER_ROLE where id in (select roleId from PAPER_USERS_ROLE where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.Set.class,many = @Many(select = "com.syh.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所用角色信息
     * @return
     */
    @Select("select * from PAPER_ROLE")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc")
    })
    List<Role> findAll();

    /**
     * 查询角色权限
     * @param rid
     * @param pid
     * @return
     */
    @Select("SELECT * FROM PAPER_ROLE_PERMISSION where permissionId=#{permissionId} and roleId=#{roleId}")
    RolePermission findByPermissionIdAndRoleId(@Param(value = "roleId") String rid,@Param(value = "permissionId") String pid);

    /**
     * 保存角色权限
     * @param rolePermissionSave
     * @return
     */
    @Insert("Insert into PAPER_ROLE_PERMISSION values(#{permissionId},#{roleId})")
    int addPermission(RolePermission rolePermissionSave);

    /**
     * 通过角色id和权限id查询角色权限
     * @param roleId 角色id
     * @param permissionId 权限id
     * @return
     */
    @Select("SELECT * FROM paper_role_permission WHERE permissionId=#{permissionId} AND roleId=#{roleId}")
    RolePermission findByRoleIdAndPreId(@Param(value = "roleId") String roleId,@Param(value = "permissionId") String permissionId);

    /**
     * 删除角色权限
     * @param rid
     * @param pid
     * @return
     */
    @Delete("delete from paper_role_permission WHERE permissionId=#{permissionId} AND roleId=#{roleId}")
    int delUserRole(@Param(value = "roleId")String rid, @Param(value = "permissionId")String pid);
}
