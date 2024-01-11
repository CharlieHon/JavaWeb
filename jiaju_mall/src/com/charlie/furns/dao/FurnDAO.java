package com.charlie.furns.dao;

import com.charlie.furns.entity.Furn;

import java.util.List;

public interface FurnDAO {
    /**
     * 返回所有家具信息的集合，后面再考虑分页
     * @return
     */
    public List<Furn> queryFurns();

    // 添加家具到数据库，返回受影响的行数
    public int addFurn(Furn furn);
}
