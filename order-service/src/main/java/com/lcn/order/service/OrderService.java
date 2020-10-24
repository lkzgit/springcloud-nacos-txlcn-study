package com.lcn.order.service;

import com.lcn.order.model.Order;

public interface OrderService {

    void createOrder(int userId, int goodsId, int count);

    Order findById(int orderId);
}
