package com.syh.service;

import com.syh.domain.Order;
import com.syh.domain.Traveller;

import java.util.List;

/**
 * @description: 订单的业务层接口
 * @author: 沈煜辉
 * @create: 2019-12-07 18:46
 **/
public interface OrderService {
    /**
     * 分页查询所有订单信息
     * @param page 起始页
     * @param pageSize 页面大小
     * @return 订单集合
     */
    List<Order> findAll(int page, int pageSize);

    /**
     * 通过id级联查询会员，订单，产品，和游客
     * @param id 订单id
     * @return 订单
     */
    Order findById(String id);

    /**
     * 跟新订单状态
     * @param order
     */
    String updateOrder(Order order);

    /**
     * 保存旅客信息
     * @param traveller
     * @return
     */
    String saveTraveller(Traveller traveller);

    /**
     * 删除旅客信息
     * @param oid
     * @param tid
     * @return
     */
    String deleteTraveller(String oid, String tid);
}
