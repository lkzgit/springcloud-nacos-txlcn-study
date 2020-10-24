package com.lcn.user.service;

import com.lcn.user.model.User;

public interface UserService {

    User findById(int id);

    boolean deductBalance(int userId, int money);
}
