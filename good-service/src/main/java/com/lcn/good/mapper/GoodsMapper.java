package com.lcn.good.mapper;

import com.lcn.good.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsMapper {

    @Select("select * from goods where id = #{id}")
    Goods findById(@Param("id") int id);

    @Update("update goods set " +
            "name = #{goods.name}, " +
            "price = #{goods.price}, " +
            "inventory = #{goods.inventory} " +
            "where id = #{goods.id}")
    void update(@Param("goods") Goods goods);
}
