package com.lcn.good.service;

import com.lcn.good.model.Goods;

public interface GoodsService {

    Goods findById(int id);

    boolean deductInventory(int goodsId, int count);
}
