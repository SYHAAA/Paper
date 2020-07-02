package com.syh.service;

import com.syh.domain.Route;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @description: 收藏路线业务层接口
 * @author: 沈煜辉
 * @create: 2019-12-13 16:26
 **/
public interface FavoriteService {

    /**
     * 查看用户收藏的旅游产品
     * @param id 用户id
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Route> findFavorite(String id, Integer pageNum, Integer pageSize);

    /**
     * 判断商品是否被收藏
     * @param rid
     * @param session
     * @return
     */
    String isFavorite(Integer rid, HttpSession session);

    /**
     * 添加收藏
     * @param rid
     * @param session
     * @return
     */
    String saveFavorite(Integer rid, HttpSession session);

    /**
     * 取消收藏
     * @param rid
     * @param session
     * @return
     */
    String cancelFavorite(Integer rid, HttpSession session);
}
