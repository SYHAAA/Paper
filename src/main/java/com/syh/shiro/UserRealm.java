package com.syh.shiro;

import com.syh.dao.UserDao;
import com.syh.domain.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: shiro的用户数据源
 * @author: 沈煜辉
 * @create: 2019-12-09 11:05
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        添加角色集合
        authorizationInfo.setRoles(userDao.getUserRole((String) principalCollection.getPrimaryPrincipal()));
//        添加权限集合
        authorizationInfo.setStringPermissions(userDao.getUserPermission((String) principalCollection.getPrimaryPrincipal()));
        return authorizationInfo;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserInfo user = userDao.findByUserName(authenticationToken.getPrincipal().toString());
        if(user==null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
    }
}
