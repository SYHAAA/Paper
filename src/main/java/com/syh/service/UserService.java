package com.syh.service;

import com.github.pagehelper.PageInfo;
import com.syh.domain.UserInfo;

/**
 * @description: 用户的业务层接口
 * @author: 沈煜辉
 * @create: 2019-12-09 09:58
 **/
public interface UserService {

    /**
     * 通过用户名查询用户对象
     * @param username
     * @return
     */
    UserInfo findUserByName(String username);

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    String register(UserInfo userInfo);


    /**
     * 通过电话号码查询用户
     * @param telNum
     * @return
     */
    UserInfo findByPhoneNum(String telNum);
    /**
     * 分页查询所有的用户列表
     * @param pageNum
     * @param pageSize
     * @param userInfo
     * @return
     */
    PageInfo<UserInfo> findAll(Integer pageNum, Integer pageSize, UserInfo userInfo);

    /**
     * 业务查询用户详情
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    String updateUser(UserInfo userInfo);

    /**
     * 给用户添加角色
     * @param uid
     * @param rid
     * @return
     */
    String addRole(String uid, String rid);

    /**
     * 删除用户角色
     * @param uid
     * @param rid
     * @return
     */
    String delRole(String uid, String rid);
}
