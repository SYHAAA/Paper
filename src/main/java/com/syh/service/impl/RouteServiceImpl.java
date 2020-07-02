package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.syh.dao.RoleDao;
import com.syh.dao.RouteDao;
import com.syh.domain.Route;
import com.syh.domain.RouteExt;
import com.syh.domain.RouteImg;
import com.syh.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @description: 旅游线路service实现类
 * @author: 沈煜辉
 * @create: 2019-12-11 19:35
 **/
@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;

    @Override
    public List<Route> findAll(Integer cid, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return routeDao.findAll(cid);
    }

    @Override
    public Route findByRouteId(Integer rid) {
        return routeDao.findByRouteId(rid);
    }

    @Override
    public List<Route> findRouteWithFavorite(Integer pageNum, Integer pageSize, Route route) {
        PageHelper.startPage(pageNum,pageSize);
        return routeDao.findRouteWithFavorite(route);
    }

    @Override
    public List<Route> findPopularity() {
        return routeDao.findPopularity();
    }

    @Override
    public List<Route> findNewest() {
        return routeDao.findNewest();
    }

    @Override
    public List<Route> findPresentation() {
        return routeDao.findPresentation();
    }

    @Override
    public List<Route> findDomestic() {
        return routeDao.findDomestic();
    }

    @Override
    public List<Route> findForeign() {
        return routeDao.findForeign();
    }

    @Override
    public List<Route> findAllByCname(String routeName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = routeDao.findAllByCname(routeName);
        return routes;
    }

    @Override
    public List<Route> findHot() {
        List<Route> routes = routeDao.findHot();
        return routes;
    }

    @Override
    public List<Route> findAllRoute(Integer pageNum, Integer pageSize, Route route) {
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = routeDao.findAllRoute(route);
        return routes;
    }

    @Override
    public String deletePro(String rid) {
        int result = routeDao.deletePro(rid);
        if (result>0){
            return "1";
        }
        return "0";
    }

    @Override
    public String deleteManyPro(String[] ids) {
        int result = routeDao.deleteManyPro(ids);
        if (result>0){
            return "1";
        }
        return "0";
    }

    @Override
    public String updateRoute(Route route) {
        int result = routeDao.updateRoute(route);
        if (result>0){
            return "1";
        }
        return "0";
    }

    @Override
    public String saveRoute(Route route) {
        routeDao.saveRoute(route);
        return "1";
    }

}
