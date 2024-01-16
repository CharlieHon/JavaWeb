package com.charlie.furns.service;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;

import java.util.List;

public interface FurnService {
    /**
     * 返回家具信息
     * @return
     */
    public List<Furn> queryFurns();

    // 添加家具
    public boolean addFurn(Furn furn);

    // 根据id删除家具信息
    public boolean deleteFurnById(int id);

    // 根据id查询家具
    public Furn queryFurnById(int id);

    // 修改指定id的家具信息
    public boolean updateFurn(Furn furn);

    // 根据传入的pageNo和pageSize，返回对应的page对象
    public Page<Furn> page(int pageNo, int pageSize);

    // 根据传入的家具名字、pageNo和pageSize，返回对应的page对象
    public Page<Furn> pageByName(String name, int pageNo, int pageSize);
}
