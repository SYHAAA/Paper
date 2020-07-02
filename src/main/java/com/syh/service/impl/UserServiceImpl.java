package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.syh.dao.UserDao;
import com.syh.domain.UserInfo;
import com.syh.domain.UserRole;
import com.syh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户的业务层实现类
 * @author: 沈煜辉
 * @create: 2019-12-09 09:58
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserInfo findUserByName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public String register(UserInfo userInfo) {
        if(userInfo==null){
            return "0";
        }else{
            UserInfo byUserName = userDao.findByUserName(userInfo.getUserName());
            if(byUserName!=null){
                return "1";
            }
            UserInfo userByUserPhoneNum = userDao.findUserByUserPhoneNum(userInfo.getPhoneNum());
            if(userByUserPhoneNum!=null){
                return "2";
            }
            UserInfo userByEmail = userDao.findUserByEmail(userInfo.getEmail());
            if(userByEmail!=null){
                return "2";
            }
        }
        userDao.saveUser(userInfo);
        return "3";
    }

    @Override
    public UserInfo findByPhoneNum(String telNum){
        UserInfo userInfo = userDao.findUserByUserPhoneNum(telNum);
        return userInfo;
    }

    @Override
    public PageInfo<UserInfo> findAll(Integer pageNum, Integer pageSize, UserInfo userInfo) {
        if (pageNum <= 1){
            pageNum = 1;
        }
        if (pageSize <= 10){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtil.isEmpty(userInfo.getUserName())){
            userInfo.setUserName(null);
        }
        if (StringUtil.isEmpty(userInfo.getName())){
            userInfo.setName(null);
        }
        List<UserInfo> list = userDao.findAll(userInfo);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    public UserInfo findById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public String updateUser(UserInfo userInfo) {
        if (userInfo==null||StringUtil.isEmpty(userInfo.getId())){
            return "0";
        }
        int result = userDao.updateUser(userInfo);
        if (result>0){
            return "1";
        }
        return "0";
    }

    @Override
    public String addRole(String uid, String rid) {
        UserRole userRole = userDao.findByUserIdAndRoleId(uid,rid);
        int result = 0;
        if (userRole==null){
            UserRole userRoleSave = new UserRole();
            userRoleSave.setRoleId(rid);
            userRoleSave.setUserId(uid);
            result = userDao.addRole(userRoleSave);
        }else{
            return "2";
        }
        if (result>0){
            return "1";
        }
        return "0";
    }

    @Override
    public String delRole(String uid, String rid) {
        if (StringUtil.isEmpty(uid)||StringUtil.isEmpty(rid)){
            return "0";
        }
        UserRole userRole = userDao.findByUserIdAndRoleId(uid, rid);
        if (userRole!=null){
            int result = userDao.delUserRole(uid,rid);
            if (result>0){
                return "1";
            }
            return "2";
        }
        return "2";
    }
}
