package com.lcn.order.feign;

import com.lcn.order.model.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserFeign {

    @RequestMapping(value = "/api/user/balance/deduct", method = RequestMethod.GET)
    ResponseData deductBalance(@RequestParam("userId") Integer userId, @RequestParam("money") Integer money);

    @RequestMapping(value = "/api/user/info", method = RequestMethod.GET)
    public ResponseData info(@RequestParam("userId") Integer userId);
}
