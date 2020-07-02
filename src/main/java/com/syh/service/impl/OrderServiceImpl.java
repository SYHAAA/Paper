package com.syh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.syh.dao.OrderDao;
import com.syh.dao.TravellerDao;
import com.syh.domain.Order;
import com.syh.domain.Traveller;
import com.syh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 订单的业务层实现类
 * @author: 沈煜辉
 * @create: 2019-12-07 18:47
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private TravellerDao travellerDao;
    @Override
    public List<Order> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Order> orders = orderDao.findAll();
        return orders;
    }

    @Override
    public Order findById(String id) {
        Order order = orderDao.findById(id);
        return order;
    }

    @Override
    public String updateOrder(Order order) {
        if (order == null || StringUtil.isEmpty(order.getId())||StringUtil.isEmpty(order.getOrderNum())){
            return "2";
        }
        Order orderSel = orderDao.findById(order.getId());
        if (StringUtil.isEmpty(orderSel.getOrderNum())){
            orderDao.updateOrder(order);
            return "0";
        }
        if (orderSel.getOrderNum().equals(order.getOrderNum())){
            orderDao.updateOrder(order);
            return "0";
        }else{
            return "1";
        }
    }

    @Override
    public String saveTraveller(Traveller traveller) {
        String name = traveller.getName();
        Traveller byName = travellerDao.findByName(name);
        String id = null;
        if (byName==null){
            travellerDao.saveTraveller(traveller);
            id = traveller.getId();
        }else{
            id = byName.getId();
        }
        orderDao.saveOrderTra(traveller.getOid(),id);
        return "1";
    }

    @Override
    public String deleteTraveller(String oid, String tid) {
        Traveller traveller = travellerDao.findById(tid);
        if (traveller == null){
            return "0";
        }
        travellerDao.deleteTraveller(oid,tid);
        return "1";
    }
}
