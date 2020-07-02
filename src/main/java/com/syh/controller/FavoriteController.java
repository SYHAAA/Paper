package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Route;
import com.syh.domain.UserInfo;
import com.syh.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 收藏路线controller
 * @author: 沈煜辉
 * @create: 2019-12-13 16:21
 **/
@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/isFavorite.do")
    public String isFavorite(Integer rid,HttpSession session){
        return favoriteService.isFavorite(rid,session);
    }

    @RequestMapping("/saveFavorite.do")
    public String saveFavorite(Integer rid,HttpSession session){
        return favoriteService.saveFavorite(rid,session);
    }

    @RequestMapping("/cancelFavorite.do")
    public String cancelFavorite(Integer rid,HttpSession session){
        return favoriteService.cancelFavorite(rid,session);
    }

    @RequestMapping("/findFavorite.do")
    public PageInfo<Route> findFavorite(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user==null){
            return null;
        }
        List<Route> routes = favoriteService.findFavorite(user.getId(),pageNum,pageSize);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return pageInfo;
    }
}
