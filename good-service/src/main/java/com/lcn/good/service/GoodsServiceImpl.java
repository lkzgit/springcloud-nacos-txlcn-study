package com.lcn.good.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.lcn.good.mapper.GoodsMapper;
import com.lcn.good.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findById(int id)
    {
        return goodsMapper.findById(id);
    }

    @LcnTransaction
    @Override
    public boolean deductInventory(int goodsId, int count)
    {
        Goods goods = findById(goodsId);
        if (goods != null && goods.getInventory() >= count)
        {
            goods.setInventory(goods.getInventory() - count);
            goodsMapper.update(goods);
            return true;
        }

        return false;
    }

}
