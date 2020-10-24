package com.lcn.order.model;

import lombok.Data;

@Data
public class Order
{
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String userName;
    private String goodsName;
    private Integer goodsCount;
    private Integer price;
    private Integer totalPrice;

}
