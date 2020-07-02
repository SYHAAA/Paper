package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Product;
import com.syh.domain.UserInfo;
import com.syh.service.UserService;
import com.syh.utils.MessageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @description: 用户 controller
 * @author: 沈煜辉
 * @create: 2019-12-09 09:57
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/login.do")
    public String login(UserInfo user, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "UnknownAccount";
        } catch (IncorrectCredentialsException e){
            return "IncorrectCredentials";
        }catch (Exception e){
            return "exception";
        }
        UserInfo userInfo = userService.findUserByName(user.getUserName());
        session.setAttribute("user",userInfo);
        return userInfo.getIsAdmin().toString();
    }

    @RequestMapping("/register.do")
    public String register(UserInfo userInfo){
        String msg = userService.register(userInfo);
        return msg;
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "1";
    }

    @RequestMapping("/loginMsg.do")
    public String loginMsg(@RequestParam(value = "telNum") String telNum,@RequestParam(value = "code")String code){
        UserInfo userInfo = userService.findByPhoneNum(telNum);
        if (userInfo==null){
            MessageUtil.getRequest2(telNum,code);
            return "1";
        }else{
            return "2";
        }
    }

    @RequestMapping("/findUserBySession.do")
    public UserInfo findUserBySession(HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        return user;
    }

    @RequestMapping("/findAll.do")
    public PageInfo<UserInfo> findAll(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10")Integer pageSize,UserInfo userInfo){
        return userService.findAll(pageNum,pageSize,userInfo);
    }

    @RequestMapping("/findById.do")
    public UserInfo findById(String id){
        return userService.findById(id);
    }

    @RequestMapping("/updateUser.do")
    public String updatePro(UserInfo userInfo){
        return userService.updateUser(userInfo);
    }

    @RequestMapping("/addRole.do")
    public String addRole(String uid,String rid){
        return userService.addRole(uid,rid);
    }

    @RequestMapping("/delRole.do")
    public String delRole(String uid,String rid){
        return userService.delRole(uid,rid);
    }
}
