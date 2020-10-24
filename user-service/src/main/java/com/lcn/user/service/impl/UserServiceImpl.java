package com.lcn.user.service.impl;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.lcn.user.mapper.UserMapper;
import com.lcn.user.model.User;
import com.lcn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(int id)
    {
        return userMapper.findById(id);
    }

    @LcnTransaction
    @Override
    public boolean deductBalance(int userId, int money)
    {
        User user = findById(userId);
        if (user != null && user.getBalance() >= money)
        {
            user.setBalance(user.getBalance() - money);
            userMapper.update(user);
            return true;
        }

        return false;
    }
}
