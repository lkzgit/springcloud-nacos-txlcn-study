package com.lcn.user.controller;

import com.lcn.user.model.ResponseData;
import com.lcn.user.model.User;
import com.lcn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

public class UserCobtroller {

    @GetMapping("test")
    public String test(){
        return "test user";
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseData info(@RequestParam("userId") Integer userId)
    {
        User user = userService.findById(userId);
        if (user != null)
        {
            return new ResponseData(ResponseData.STATUS_OK, user, "获取成功！");
        }
        else
        {
            return new ResponseData(ResponseData.STATUS_FAILED, null, "获取失败！");
        }
    }

    @RequestMapping(value = "/balance/deduct", method = RequestMethod.GET)
    public ResponseData deductBalance(@RequestParam("userId") Integer userId,@RequestParam("money") Integer money)
    {
        if (userService.deductBalance(userId, money))
        {
            return new ResponseData(ResponseData.STATUS_OK, null, "扣减成功！");
        }
        else
        {
            return new ResponseData(ResponseData.STATUS_FAILED, null, "扣减失败！");
        }
    }
}
