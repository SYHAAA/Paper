package com.syh.dao;

import com.syh.domain.Route;
import com.syh.domain.RouteExt;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * @description: 旅游线路dao层接口
 * @author: 沈煜辉
 * @create: 2019-12-11 19:35
 **/
public interface RouteDao {
    /**
     * dao 查询所用路线信息
     * @param cid
     * @return
     */
    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rflag\", \"rdate\", \"isThemeTour\" ,\"count\", \"cid\", \"rimage\", \"sid\" from \"tab_route\" where \"cid\"=#{cid} and \"rflag\"=1")
    List<Route> findAll(Integer cid);

    /**
     * 通过商品id查询商品详情
     * @param rid 商品id
     * @return
     */
    @Results(id = "BaseResult",value = {
        @Result(column = "rid",property = "rid",javaType = Integer.class),
        @Result(column = "rname",property = "rname",javaType = String.class),
        @Result(column = "price",property = "price",javaType = Double.class),
        @Result(column = "routeIntroduce",property = "routeIntroduce",javaType = String.class),
        @Result(column = "rflag",property = "rflag",javaType = String.class),
        @Result(column = "rdate",property = "rdate",javaType = String.class),
        @Result(column = "isThemeTour",property = "isThemeTour",javaType = String.class),
        @Result(column = "count",property = "count",javaType = Integer.class ),
        @Result(column = "rimage",property = "rimage",javaType = String.class),
        @Result(column = "cid",property = "cid",javaType = Integer.class),
        @Result(column = "sid",property = "seller",javaType = Integer.class,one = @One(select = "com.syh.dao.SellerDao.findSellerBySid",fetchType = FetchType.DEFAULT)),
        @Result(column = "cid",property = "category",javaType = Integer.class,one = @One(select = "com.syh.dao.CategoryDao.findCategoryByCid",fetchType = FetchType.DEFAULT)),
        @Result(property = "routeImgList",column = "rid",javaType = List.class,many = @Many(select = "com.syh.dao.RouteImgDao.findByRid",fetchType = FetchType.LAZY))
    })
    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rflag\", \"rdate\", \"isThemeTour\" ,\"count\", \"rimage\", \"sid\",\"cid\" from \"tab_route\" where \"rid\"=#{rid}")
    Route findByRouteId(Integer rid);

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"isThemeTour\" ,\"count\", \"rimage\", \"sid\",\"cid\" from \"tab_route\" where \"rid\"=#{rid} and \"rflag\"=1")
    Route findRouteByID(Integer rid);

    @Update("update \"tab_route\" set \"count\"=\"count\"+1 where \"rid\"=#{rid} ")
    void updateCount(Integer rid);

    /**
     *  带有条件的查询收藏路线
     * @param route
     * @return
     */

    @Select("<script>" +
            "select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rflag\", \"rdate\", \"isThemeTour\" ,\"count\", \"cid\", \"rimage\", \"sid\" ,\"sourceId\" from \"tab_route\"\n" +
            "        <where>\n" +
            "            <if test=\"rname != null\">\n" +
            "                and \"rname\" like '%${rname}%'\n" +
            "            </if>\n" +
            "            <if test=\"priceLow != null\">\n" +
            "                and \"price\" > #{priceLow}\n" +
            "            </if>\n" +
            "            <if test=\"priceHigh != null\">\n" +
            "                and #{priceHigh} > \"price\"\n" +
            "            </if>\n" +
            "            and \"count\">10 and \"rflag\"=1\n" +
            "        </where>\n" +
            "        order by \"count\" desc"+
            "</script>")
    List<Route> findRouteWithFavorite(Route route);

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"  from (select t.*,ROWNUM from \"tab_route\" t where \"rflag\"=1 order by \"count\" desc) where ROWNUM between 1 and 4 ")
    List<Route> findPopularity();

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"  from (select t.*,ROWNUM from \"tab_route\" t where \"rflag\"=1 order by \"rdate\" desc) where ROWNUM between 1 and 4  ")
    List<Route> findNewest();

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"  from (select t.*,ROWNUM from \"tab_route\" t where \"rflag\"=1 and \"isThemeTour\"=1) where ROWNUM between 1 and 4  ")
    List<Route> findPresentation();

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"  from (select t.*,ROWNUM from \"tab_route\" t where \"rflag\"=1 and \"cid\"=5 order by \"count\" asc ) where ROWNUM between 1 and 6 ")
    List<Route> findDomestic();

    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"  from (select t.*,ROWNUM from \"tab_route\" t where \"rflag\"=1 and \"cid\"=4 order by \"count\" asc ) where ROWNUM between 1 and 6")
    List<Route> findForeign();

    /**
     * 通过名称模糊查询线路
     * @param routeName
     * @return
     */
    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\" from \"tab_route\" where  \"rname\" like '%${routeName}%' and \"rflag\"='1'")
    List<Route> findAllByCname(@Param(value = "routeName") String routeName);

    /**
     * 随机查询五个旅游路线
     * @return
     */
    @ResultMap("BaseResult")
    @Select("select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\" from (select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\" from \"tab_route\" where \"rflag\"='1' order by DBMS_RANDOM.VALUE(5,1)) where ROWNUM<6")
    List<Route> findHot();

    /**
     * 减少收藏数
     * @param rid
     */
    @Update("update \"tab_route\" set \"count\"=\"count\"-1 where \"rid\"=#{rid} ")
    void updateCountDown(Integer rid);

    /**
     * dao查找所用的旅游路线
     * @param route
     * @return
     */
    @Select("<script>" +
            "select \"rid\", \"rname\", \"routeIntroduce\", \"price\", \"rdate\", \"count\", \"cid\", \"rimage\"，\"isThemeTour\" from \"tab_route\""
            + " <where>"
            + " <if test=\"startTime != null\">"
            + " AND to_date(\"rdate\",'yyyy-mm-dd hh:mi:ss') >= #{startTime}"
            + " </if>"
            + " <if test=\"endTime != null\">"
            + " AND #{endTime} >= to_date(\"rdate\",'yyyy-mm-dd hh:mi:ss')"
            + " </if>"
            + " <if test=\"rname != null \">"
            + " AND \"rname\" like '%${rname}%'"
            + " </if>\n"
            + " <if test=\"priceLow != null \">"
            + " AND \"price\" >= #{priceLow}"
            + " </if>\n"
            + " <if test=\"priceHigh != null \">"
            + " AND #{priceHigh} >= \"price\" "
            + " </if>\n"
            + " <if test=\"cid != null \">"
            + " AND  \"cid\" = #{cid}"
            + " </if>\n"
            + "and \"rflag\"='1'"
            + " </where> order by \"rdate\" DESC" +
            "</script>")
    List<Route> findAllRoute(Route route);

    /**
     * 删除旅游路线
     * @param rid
     */
    @Update("update \"tab_route\" set \"rflag\"='0' where \"rid\"=#{rid}")
    int deletePro(String rid);

    /**
     * 批量删除路线
     * @param ids
     */
    @Update(" <script> " +
            " update \"tab_route\" set \"rflag\"='0' where \"rid\" in" +
            " <foreach collection=\"ids\" open=\"(\" close=\")\" item=\"id\" separator=\",\">" +
            " #{id}" +
            " </foreach>" +
            " </script>")
    int deleteManyPro(@Param("ids") String[] ids);

    /**
     *  更新产品信息
     * @param route
     */
    @Update("update \"tab_route\" set \"rname\"=#{rname},\"routeIntroduce\"=#{routeIntroduce},\"rdate\"=#{rdate},\"price\"=#{price},\"cid\"=#{cid},\"count\"=#{count},\"isThemeTour\"=#{isThemeTour},\"rflag\"=#{rflag} where \"rid\"=#{rid}")
    int updateRoute(Route route);

    /**
     * 增加旅游信息
     * @param route
     */
    @Insert("insert into  \"tab_route\"(\"rid\",\"rname\",\"routeIntroduce\",\"rdate\",\"price\",\"cid\",\"count\",\"isThemeTour\",\"rflag\",\"rimage\",\"sid\") values (seq_route.nextval,#{rname},#{routeIntroduce},#{rdate},#{price},#{cid},#{count},#{isThemeTour},#{rflag},#{rimage,},'1')")
    void saveRoute(Route route);
}
