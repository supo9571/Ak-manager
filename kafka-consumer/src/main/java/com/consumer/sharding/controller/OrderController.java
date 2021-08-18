package com.consumer.sharding.controller;

import com.consumer.sharding.domain.Order;
import com.consumer.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/8/18
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/get")
    public String getOrder() {
        List<Order> list = orderService.getAll("2019-06-14 0:0:0");
        System.out.println(list);
        return "OK";
    }

}
