package com.syh.dao;

import com.syh.domain.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 沈煜辉
 * @date 2019/9/27 16:05
 * @describe 订单的dao接口
 */
public interface OrderDao {


    /**
     * 分页查询所用订单
     * @return
     */
    @Select("select ID,ORDERNUM,ORDERTIME,PEOPLECOUNT,ORDERDESC,PAYTYPE,ORDERSTATUS,PRODUCTID,MEMBERID,ORDERPRICE from PAPER_ORDERS")
    @Results(id="OrderMap",value = {
            @Result(column = "ID",property = "id"),
            @Result(column = "ORDERNUM",property = "orderNum"),
            @Result(column = "ORDERTIME",property = "orderTime"),
            @Result(column = "PEOPLECOUNT",property = "peopleCount"),
            @Result(column = "ORDERDESC",property = "orderDesc"),
            @Result(column = "PAYTYPE",property = "payType"),
            @Result(column = "ORDERSTATUS",property = "orderStatus"),
            @Result(column = "ORDERPRICE",property = "orderPrice"),
            @Result(property = "route",column = "PRODUCTID",javaType = Route.class,one = @One(select = "com.syh.dao.RouteDao.findRouteByID")),
            @Result(property = "userInfo",column = "MEMBERID",javaType = UserInfo.class,one = @One(select = "com.syh.dao.UserDao.findUserById")),
    })
    List<Order> findAll();

    /**
     * 通过id级联查询会员，订单，产品，和游客
     * @param id
     * @return
     */
    @Select("select id,orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId,memberId,orderPrice from PAPER_ORDERS where id=#{id}")
    @Results(id = "orderAll",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "orderPrice",property = "orderPrice"),
            @Result(property = "route",column = "productId",javaType = Product.class,one = @One(select = "com.syh.dao.RouteDao.findRouteByID")),
            @Result(property = "userInfo",column = "memberId",javaType = UserInfo.class,one = @One(select = "com.syh.dao.UserDao.findUserById")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.syh.dao.TravellerDao.findTravellerOrderId",fetchType = FetchType.LAZY))
    })
    Order findById(String id);

    /**
     * 通过订单编号查找订单
     * @param orderNum
     * @return
     */
    @Select("select id,orderNum from PAPER_ORDERS where orderNum=#{orderNum}")
    Order findByNum(String orderNum);

    /**
     * 更新订单
     * @param order
     */
    @Update("update PAPER_ORDERS set orderNum=#{orderNum},peopleCount=#{peopleCount},orderDesc=#{orderDesc},payType=#{payType},orderPrice=#{orderPrice},orderStatus=#{orderStatus} where id=#{id}")
    void updateOrder(Order order);

    /**
     * 保存订单旅客
     * @param oid
     * @param tid
     */
    @Insert("insert into PAPER_ORDER_TRAVELLER values (#{oid},#{tid})")
    void saveOrderTra(@Param("oid") String oid,@Param("tid") String tid);
}
