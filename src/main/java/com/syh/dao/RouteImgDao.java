package com.syh.dao;

import com.syh.domain.RouteImg;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 商品信息图集
 * @author: 沈煜辉
 * @create: 2019-12-12 16:59
 **/
public interface RouteImgDao {

    /**
     * 通过旅游商品id值查询图集
     * @param rid
     * @return
     */
    @Select("select \"rgid\",\"rid\",\"bigPic\",\"smallPic\" from \"tab_route_img\" where \"rid\"=#{rid}")
    RouteImg findByRid(Integer rid);
}
