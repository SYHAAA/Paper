package com.syh.dao;

import com.syh.domain.Role;
import com.syh.domain.UserInfo;
import com.syh.domain.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @description: 用户的dao接口
 * @author: 沈煜辉
 * @create: 2019-12-09 09:59
 **/
public interface UserDao {

    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    @Results(id = "resultMap",value = {
            @Result(id = true,column = "ID",property = "id"),
            @Result(column = "EMAIL",property = "email"),
            @Result(column = "USERNAME",property = "userName"),
            @Result(column = "PHONENUM",property = "phoneNum"),
            @Result(column = "NAME",property = "name"),
            @Result(column = "ISADMIN",property = "isAdmin"),
            @Result(column = "STATUS",property = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.Set.class,many = @Many(select = "com.syh.dao.RoleDao.findRoleByUserId"))
    })
    @Select("select ID,EMAIL,USERNAME,PASSWORD,PHONENUM,NAME,ISADMIN,STATUS from paper_users where id=#{id}")
    UserInfo findUserById(String id);

    /**
     * 通过用户名获取用户的角色
     * @param primaryPrincipal 用户名
     * @return 角色集合
     */
    @Select("select r.ROLENAME from PAPER_USERS u" +
            " left join PAPER_USERS_ROLE ur on u.ID=ur.USERID" +
            " left join PAPER_ROLE r on ur.ROLEID=r.ID" +
            " where u.ISADMIN=1 and u.USERNAME=#{username} and status=1")
    Set<String> getUserRole(String primaryPrincipal);

    /**
     * 通过用户的名称获取用户的权限
     * @param primaryPrincipal 用户名
     * @return 权限集合
     */
    @Select("select PERMISSIONNAME from PAPER_PERMISSION where ID in (" +
            "    select PERMISSIONID from PAPER_ROLE_PERMISSION where ROLEID in (" +
            "        select ROLEID from PAPER_USERS_ROLE where USERID in (" +
            "            select id from PAPER_USERS where USERNAME='test'" +
            "        )" +
            "    )" +
            ")")
    Set<String> getUserPermission(String primaryPrincipal);

    @Select("select ID, EMAIL, USERNAME, PASSWORD, PHONENUM, STATUS, NAME, ISADMIN from PAPER_USERS where username=#{username} and status=1")
    UserInfo findByUserName(String username);

    /**
     * 通过电话号码查询用户对象，确保电话唯一
     * @param phoneNum
     * @return
     */
    @Select("select ID, EMAIL, USERNAME, PASSWORD, PHONENUM, STATUS, NAME, ISADMIN from PAPER_USERS where phoneNum=#{phoneNum} and status=1")
    UserInfo findUserByUserPhoneNum(String phoneNum);

    /**
     * 通过邮箱查询用户名
     * @param email
     * @return
     */
    @Select("select ID, EMAIL, USERNAME, PASSWORD, PHONENUM, STATUS, NAME, ISADMIN from PAPER_USERS where email=#{email} and status=1")
    UserInfo findUserByEmail(String email);

    @Insert("insert into PAPER_USERS(EMAIL, USERNAME, PASSWORD, PHONENUM, STATUS, NAME, ISADMIN) values(#{email},#{userName},#{password},#{phoneNum},1,#{name},0)")
    void saveUser(UserInfo userInfo);

    /**
     * 带有条件的查询所有用户
     * @param userInfo
     * @return
     */
    @Select("<script>" +
            "select ID,EMAIL,USERNAME,PASSWORD,PHONENUM,STATUS,NAME,ISADMIN from PAPER_USERS" +
            "<where>" +
            "<if test=\"userName != null\">" +
            "and USERNAME like '%${userName}%'" +
            "</if>" +
            "<if test=\"isAdmin != null\">" +
            "and ISADMIN = #{isAdmin}" +
            "</if>" +
            "<if test=\"name != null\">" +
            "and NAME like '%${name}%'" +
            "</if>" +
            "</where>" +
            "</script>")
    List<UserInfo> findAll(UserInfo userInfo);


    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    @Update("update PAPER_USERS set USERNAME=#{userName},PASSWORD=#{password},EMAIL=#{email},ISADMIN=#{isAdmin},STATUS=#{status} where ID=#{id}")
    int updateUser(UserInfo userInfo);

    /**
     * 通过用户id和角色id查询用户角色对象
     * @param uid
     * @param rid
     * @return
     */
    @Select("SELECT * FROM paper_users_role where userId=#{userId} and roleId=#{roleId}")
    UserRole findByUserIdAndRoleId(@Param(value = "userId") String uid,@Param(value = "roleId") String rid);

    /**
     * 保存用户角色信息
     * @param userRole
     * @return
     */
    @Insert("Insert into paper_users_role values(#{userId},#{roleId})")
    int addRole(UserRole userRole);

    /**
     * 删除用户角色
     * @param uid
     * @param rid
     * @return
     */
    @Delete("delete from paper_users_role where userId=#{userId} and roleId=#{roleId}")
    int delUserRole(@Param(value = "userId")String uid, @Param(value = "roleId")String rid);
}
