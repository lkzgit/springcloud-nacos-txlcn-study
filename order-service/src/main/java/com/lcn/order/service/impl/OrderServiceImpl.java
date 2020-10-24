package com.lcn.order.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.lcn.order.exception.MyException;
import com.lcn.order.feign.GoodFeign;
import com.lcn.order.mapper.OrderMapper;
import com.lcn.order.model.Order;
import com.lcn.order.model.ResponseData;
import com.lcn.order.service.OrderService;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GoodFeign goodFeign;
    @LcnTransaction
    @Override
    public void createOrder(int userId, int goodsId, int count) {
        String userName = "";
        String goodsName = "";
        int userBalance = 0;
        int price = 0;
        int totalPrice = 0;
        int goodsInventory = 0;

        //用户信息
        ResponseData response1 = restTemplate.getForObject("http://user-service/api/user/info?userId=" + userId, ResponseData.class);
        if (response1 != null) {
            if (response1.getStatusCode() == ResponseData.STATUS_OK) {
                JSONObject json = new JSONObject(JSON.toJSONString(response1.getData()));
                userName = json.optString("name");
                userBalance = json.optInt("balance");
            } else {
                throw new MyException(ResponseData.STATUS_FAILED, "获取用户信息失败！");
            }
        }

        //商品信息
        ResponseData response2 = goodFeign.info(goodsId);
        //ResponseData response2 = restTemplate.getForObject("http://good-service/api/goods/info?goodsId=" + goodsId, ResponseData.class);
        if (response2 != null) {
            if (response2.getStatusCode() == ResponseData.STATUS_OK) {
                JSONObject json = new JSONObject(JSON.toJSONString(response2.getData()));
                goodsName = json.optString("name");
                price = json.optInt("price");
                goodsInventory = json.optInt("inventory");
            } else {
                throw new MyException(ResponseData.STATUS_FAILED, "获取商品信息失败！");
            }
        }

        //生成订单
        if (goodsInventory >= count) {
            totalPrice = count * price;

            if (userBalance >= totalPrice) {
                System.out.println("=======================订单信息=======================");
                System.out.println("用户名：" + userName);
                System.out.println("商品名：" + goodsName);
                System.out.println("购买数量：" + count);
                System.out.println("单价：" + price);
                System.out.println("总价：" + totalPrice);

                //扣减用户余额
                System.out.println("开始扣减用户余额");
                boolean isDeductBalanceSuccess = false;
                ResponseData response3 = restTemplate.getForObject("http://user-service/api/user/balance/deduct?userId=" + userId + "&money=" + totalPrice, ResponseData.class);
                if (response3 != null) {
                    System.out.println("扣减用户余额=" + JSON.toJSONString(response3));
                    isDeductBalanceSuccess = response3.getStatusCode() == ResponseData.STATUS_OK;
                }

                //扣减商品库存
                System.out.println("开始扣减商品库存");
                boolean isDeductInventorySuccess = false;
                ResponseData response4 = goodFeign.deductBalance(goodsId, count);
               // ResponseData response4 = restTemplate.getForObject("http://good-service/api/goods/inventory/deduct?goodsId=" + goodsId + "&count=" + count, ResponseData.class);
                if (response4 != null) {
                    System.out.println("扣减商品库存=" + JSON.toJSONString(response3));
                    isDeductInventorySuccess = response4.getStatusCode() == ResponseData.STATUS_OK;
                }

                if (!isDeductBalanceSuccess || !isDeductInventorySuccess) {
                    throw new MyException(ResponseData.STATUS_FAILED, "扣减用户余额或商品库存失败！");
                }

                Order order = new Order();
                order.setUserId(userId);
                order.setGoodsId(goodsId);
                order.setUserName(userName);
                order.setGoodsName(goodsName);
                order.setGoodsCount(count);
                order.setPrice(price);
                order.setTotalPrice(totalPrice);
                orderMapper.insert(order);

                if (count == 10) {
                    throw new MyException(ResponseData.STATUS_FAILED, "模拟异常！");
                } else {
                    System.out.println("订单信息创建成功");
                }
            } else {
                throw new MyException(ResponseData.STATUS_FAILED, "用户余额不足！");
            }
        } else {
            throw new MyException(ResponseData.STATUS_FAILED, "商品库存不足！");
        }
    }

    @Override
    public Order findById(int orderId) {
        return orderMapper.findById(orderId);
    }

}