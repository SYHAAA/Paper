package com.syh.dao;

import com.syh.domain.Route;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description: 收藏路线dao接口
 * @author: 沈煜辉
 * @create: 2019-12-13 16:27
 **/
public interface FavoriteDao {
    /**
     * dao 保护用户收藏路线
     * @param uid 用户id
     * @param rid 旅游路线id
     */
    @Insert("insert into PAPER_ORDERS(PRODUCTID,ORDERTIME,MEMBERID,ORDERSTATUS) values (#{rid},SYSDATE,#{uid},0)")
    void saveFavorite(@Param("uid") String uid,@Param("rid") Integer rid);

    @Select("select MEMBERID from PAPER_ORDERS where PRODUCTID=#{rid}")
    String findByRid(Integer rid);

    /**
     * dao查询用户收藏商品
     * @param id
     * @return
     */
    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rflag\", \"rdate\", \"isThemeTour\" ,\"count\", \"cid\", \"rimage\", \"sid\" ,\"sourceId\" from \"tab_route\" where \"rid\" in (select PRODUCTID from PAPER_ORDERS where MEMBERID=#{uid})")
    List<Route> findFavorite(String id);

    /**
     * dao删除收藏数
     * @param uid
     * @param rid
     */
    @Delete("delete from PAPER_ORDERS where MEMBERID=#{uid} and PRODUCTID=#{rid}")
    void cancelFavorite(@Param("uid") String uid,@Param("rid") Integer rid);
}
