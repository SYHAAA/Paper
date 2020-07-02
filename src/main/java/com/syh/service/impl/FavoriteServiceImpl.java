package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.syh.dao.FavoriteDao;
import com.syh.dao.RouteDao;
import com.syh.domain.Route;
import com.syh.domain.UserInfo;
import com.syh.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 收藏路线业务层实现类
 * @author: 沈煜辉
 * @create: 2019-12-13 16:26
 **/
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private RouteDao routeDao;
    @Autowired
    private FavoriteDao favoriteDao;


    @Override
    public List<Route> findFavorite(String id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = favoriteDao.findFavorite(id);
        return routes;
    }

    /**
     * 判断该路线是否被用户收藏
     * @param rid
     * @param session
     * @return
     */
    @Override
    public String isFavorite(Integer rid, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("user");
        if(user==null){
            return "3";
        }
        List<Route> favorite = favoriteDao.findFavorite(user.getId());
        for (Route r :favorite) {
            if (r.getRid().equals(rid)){
                return "1";
            }
        }
        return "0";
    }

    @Override
    public String saveFavorite(Integer rid, HttpSession session) {
        String isFavorite = isFavorite(rid, session);
        UserInfo user = (UserInfo) session.getAttribute("user");
        if ("0".equals(isFavorite)){
            favoriteDao.saveFavorite(user.getId(),rid);
            routeDao.updateCount(rid);
            return "1";
        }
        return "0";
    }

    @Override
    public String cancelFavorite(Integer rid, HttpSession session) {
        String isFavorite = isFavorite(rid, session);
        UserInfo user = (UserInfo) session.getAttribute("user");
        if ("1".equals(isFavorite)){
            favoriteDao.cancelFavorite(user.getId(),rid);
            routeDao.updateCountDown(rid);
            return "1";
        }
        return "0";
    }
}
