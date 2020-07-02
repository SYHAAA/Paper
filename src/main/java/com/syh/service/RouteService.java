package com.syh.service;

import com.syh.domain.Route;
import com.syh.domain.RouteExt;
import com.syh.domain.RouteImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: l旅游线路service接口
 * @author: 沈煜辉
 * @create: 2019-12-11 19:34
 **/
public interface RouteService {
    /**
     * 通过分类id查询所用的路线
     * @param cid
     * @return
     */
    List<Route> findAll(Integer cid,Integer pageNum,Integer pageSize);

    /**
     * 通过旅游商品id查询详情
     * @param rid 商品id
     * @return
     */
    Route findByRouteId(Integer rid);

    /**
     * 查询收藏排行榜
     * @param pageNum 起始页
     * @param pageSize 页面大小
     * @return
     */
    List<Route> findRouteWithFavorite(Integer pageNum, Integer pageSize, Route route);

    /**
     * 查询人气旅游
     * @return
     */
    List<Route> findPopularity();

    /**
     * 查询最新旅游
     * @return
     */
    List<Route> findNewest();

    /**
     * 查看主题旅游
     * @return
     */
    List<Route> findPresentation();

    /**
     * 查看国内景点
     * @return
     */
    List<Route> findDomestic();

    /**
     * 查看国外景点
     * @return
     */
    List<Route> findForeign();

    /**
     * 通过名称模糊查询旅游线路
     * @param rname
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Route> findAllByCname(String rname, Integer pageNum, Integer pageSize);

    /**
     * 热门推荐
     * @return
     */
    List<Route> findHot();

    /**
     * 寻找所有的旅游路线
     * @param pageNum
     * @param pageSize
     * @param route
     * @return
     */
    List<Route> findAllRoute(Integer pageNum, Integer pageSize, Route route);

    /**
     * 删除路由路线
     * @param rid
     */
    String deletePro(String rid);

    /**
     * 批量删除路线
     * @param ids
     */
    String deleteManyPro(String[] ids);

    /**
     * 更新旅游路线
     * @param route
     */
    String updateRoute(Route route);

    /**
     * 增加产品路线
     * @param route
     * @return
     */
    String saveRoute(Route route);
}
