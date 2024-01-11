package com.charlie.furns.service;

import com.charlie.furns.entity.Furn;

import java.util.List;

public interface FurnService {
    /**
     * 返回家具信息
     * @return
     */
    public List<Furn> queryFurns();

    // 添加家具
    public boolean addFurn(Furn furn);
}
