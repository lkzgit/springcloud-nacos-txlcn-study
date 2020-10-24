package com.lcn.good.controller;

import com.lcn.good.model.Goods;
import com.lcn.good.model.ResponseData;
import com.lcn.good.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goods")
public class GoodApplication {

    @GetMapping("test")
    public String test(){
        return "good ok";
    }

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseData info(@RequestParam("id") Integer goodsId)
    {
        Goods goods = goodsService.findById(goodsId);
        if (goods != null)
        {
            return new ResponseData(ResponseData.STATUS_OK, goods, "获取成功！");
        }
        else
        {
            return new ResponseData(ResponseData.STATUS_FAILED, null, "获取失败！");
        }
    }

    @RequestMapping(value = "/inventory/deduct", method = RequestMethod.GET)
    public ResponseData deductBalance(@RequestParam("goodsId") Integer goodsId,@RequestParam("count") Integer count)
    {
        if (goodsService.deductInventory(goodsId, count))
        {
            return new ResponseData(ResponseData.STATUS_OK, null, "扣减成功！");
        }
        else
        {
            return new ResponseData(ResponseData.STATUS_FAILED, null, "扣减失败！");
        }
    }
}
