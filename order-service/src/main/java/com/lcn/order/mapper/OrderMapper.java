package com.lcn.order.mapper;

import com.lcn.order.model.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {
    @Insert("insert into user_order(user_id, goods_id, user_name, goods_name, goods_count, price, total_price) values(" +
            "#{order.userId}, " +
            "#{order.goodsId}, " +
            "#{order.userName}, " +
            "#{order.goodsName}, " +
            "#{order.goodsCount}, " +
            "#{order.price}, " +
            "#{order.totalPrice}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(@Param("order") Order order);

    @Select("select * from user_order where id = #{id}")
    Order findById(@Param("id") int id);

}