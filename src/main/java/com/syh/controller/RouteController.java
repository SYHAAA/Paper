package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Route;
import com.syh.domain.RouteExt;
import com.syh.domain.RouteImg;
import com.syh.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 旅游路线controller
 * @author: 沈煜辉
 * @create: 2019-12-11 19:34
 **/
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;


    @RequestMapping("/deleteManyPro.do")
    public String deleteManyPro(String[] ids){
        return routeService.deleteManyPro(ids);
    }

    @RequestMapping("/deletePro.do")
    public String deletePro(String rid){
        return routeService.deletePro(rid);
    }

    @RequestMapping("/findAllRoute.do")
    public PageInfo<Route> findAllRoute(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,Route route){
        List<Route> routes = routeService.findAllRoute(pageNum,pageSize,route);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return pageInfo;
    }

    @RequestMapping("/findAll.do")
    public PageInfo<Route> findAll(Integer cid, @RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        List<Route> routes = routeService.findAll(cid,pageNum,pageSize);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return pageInfo;
    }

    @RequestMapping("/findRouteByRid.do")
    public Route findByRouteId(Integer rid){
        Route route =  routeService.findByRouteId(rid);
        return route;
    }

    @RequestMapping("/findRouteWithFavorite.do")
    public PageInfo<Route> findRouteWithFavorite(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize,Route route){
        List<Route> routes = routeService.findRouteWithFavorite(pageNum,pageSize,route);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return pageInfo;
    }

    @RequestMapping("/findPopularity.do")
    public List<Route> findPopularity(){
        return routeService.findPopularity();
    }

    @RequestMapping("/findNewest.do")
    public List<Route> findNewest(){
        return routeService.findNewest();
    }

    @RequestMapping("/findPresentation.do")
    public List<Route> findPresentation(){
        return routeService.findPresentation();
    }

    @RequestMapping("/findDomestic.do")
    public List<Route> findDomestic(){
        return routeService.findDomestic();
    }

    @RequestMapping("/findForeign.do")
    public List<Route> findForeign(){
        return routeService.findForeign();
    }

    @RequestMapping(value = "/findAllByCname.do",produces ={"application/json;charset=utf-8"})
    public PageInfo<Route> findAllByCname(String rname, @RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10")Integer pageSize){
        if(rname==""||rname==null){
            return null;
        }
        List<Route> routes = routeService.findAllByCname(rname,pageNum,pageSize);
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return pageInfo;
    }

    @GetMapping(value = "/findHot.do")
    public List<Route> findHot(){
        return routeService.findHot();
    }

    @PostMapping("/updateRoute.do")
    public String updateRoute(Route route){
        return routeService.updateRoute(route);
    }

    @PostMapping("/saveRoute.do")
    public String saveRoute(Route route){
        return routeService.saveRoute(route);
    }
}
