package com.lcn.order.feign;

import com.lcn.order.model.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("good-service")
public interface GoodFeign {

    @GetMapping("/api/goods/info")
    ResponseData info(@RequestParam("id") Integer goodsId);

    @RequestMapping(value = "/api/goods/inventory/deduct", method = RequestMethod.GET)
     ResponseData deductBalance(@RequestParam("goodsId") Integer goodsId,@RequestParam("count") Integer count);
}
