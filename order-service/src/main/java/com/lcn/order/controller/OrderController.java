package com.lcn.order.controller;

import com.lcn.order.feign.GoodFeign;
import com.lcn.order.feign.UserFeign;
import com.lcn.order.model.Order;
import com.lcn.order.model.ResponseData;
import com.lcn.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Resource
    private GoodFeign goodFeign;
    @Autowired
    private UserFeign userFeign;
    @GetMapping("test")
    public String test(){
        return "order ok";
    }
    @GetMapping("test2")
    public ResponseData test2(){
       return goodFeign.info(1);
    }
    @GetMapping("test3")
    public ResponseData test3(){
        return userFeign.info(1);
    }

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseData createOrder(Integer userId, Integer goodsId, Integer count)
    {
        orderService.createOrder(userId, goodsId, count);
        return new ResponseData(ResponseData.STATUS_FAILED, null, "下单成功！");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseData info(@RequestParam("id") Integer goodsId)
    {
        Order order = orderService.findById(goodsId);
        if (order != null)
        {
            return new ResponseData(ResponseData.STATUS_OK, order, "获取成功！");
        }
        else
        {
            return new ResponseData(ResponseData.STATUS_FAILED, null, "获取失败！");
        }
    }

}
