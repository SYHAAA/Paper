package com.syh.controller;

import com.github.pagehelper.PageInfo;
import com.syh.domain.Order;
import com.syh.domain.Traveller;
import com.syh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @description: 订单controller
 * @author: 沈煜辉
 * @create: 2019-12-07 18:46
 **/
@RestController
    @RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findAll.do")
    public PageInfo<Order> findAll(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize){
        List<Order> orders = orderService.findAll(pageNum,pageSize);
        PageInfo<Order> pageInfo = new PageInfo(orders);
        return pageInfo;
    }

    @RequestMapping("/findById.do")
    public Order findById(String id){
        Order order = orderService.findById(id);
        return  order;
    }
    @RequestMapping("/updateOrder.do")
    public String updateOrder(Order order){
        return orderService.updateOrder(order);
    }

    @PostMapping("/saveTraveller.do")
    public String saveTraveller(Traveller traveller){
        return orderService.saveTraveller(traveller);
    }

    @GetMapping("/deleteTraveller.do")
    public String deleteTraveller(String oid,String tid){
        return orderService.deleteTraveller(oid,tid);
    }
}
