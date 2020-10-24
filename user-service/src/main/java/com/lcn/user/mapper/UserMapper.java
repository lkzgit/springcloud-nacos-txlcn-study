package com.lcn.user.mapper;

import com.lcn.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper
{
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int id);

    @Update("update user set " +
            "name = #{user.name}, " +
            "balance = #{user.balance} " +
            "where id = #{user.id}")
    void update(@Param("user") User user);
}
