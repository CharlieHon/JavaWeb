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

    // 通过家具的id删除家具，返回受影响的行数
    public int deleteFurnById(int id);

    // 通过家具的id查询家具
    public Furn queryFurnById(int id);

    // 修改家具信息
    public int updateFurn(Furn furn);

    // 分析：Page的哪些属性是可以直接从数据库中获取的，就把这个填充任务放在DAO层
    public int getTotalRow();

    // 获取当前页要显示的家具数据
    // begin： 表示从第几条记录开始获取，从0开始计算
    // pageSize：表示取出多少条记录
    public List<Furn> getPageItems(int begin, int pageSize);

    // 根据家具名获取记录数
    public int getTotalRowByName(String name);

    public List<Furn> getPageItemsByName(String name, int begin, int pageSize);
}
