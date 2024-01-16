package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;

import java.util.List;

public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {

    @Override
    public List<Furn> queryFurns() {
        // 通过设置别名，解决表字段名和javabean属性名不同的问题
        // 反射是通过sql查询语句的字段找对应的属性
        String sql = "SELECT `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` imgPath" +
                " FROM furn;";
        return queryMulti(sql, Furn.class);
    }

    @Override
    public int addFurn(Furn furn) {
        String sql = "insert into furn(`id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path`)" +
                " values (null, ?, ?, ?, ?, ?, ?)";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(),
                furn.getImgPath());
    }

    @Override
    public int deleteFurnById(int id) {
        String sql = "delete from furn where id=?";
        return update(sql, id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql = "SELECT `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` imgPath from `furn` where id=?";
        return querySingle(sql, Furn.class, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "UPDATE `furn` SET `name`=?, `maker`=?, `price`=?, `sales`=?, `stock`=?, `img_path`=? WHERE id=?;";
        return update(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath(), furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql = "select count(*) from furn";
        //return (Integer) queryScalar(sql);    // Long cannot be cast to Integer
        return ((Number) queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {
        String sql = "SELECT `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` imgPath from `furn` limit ?, ?";
        return queryMulti(sql, Furn.class, begin, pageSize);
    }

    @Override
    public int getTotalRowByName(String name) {
        String sql = "select count(*) from furn where `name` like ?";
        return ((Number) queryScalar(sql, "%" + name + "%")).intValue();
    }

    @Override
    public List<Furn> getPageItemsByName(String name, int begin, int pageSize) {
        String sql = "SELECT `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` imgPath from `furn` where `name` like ? limit ?, ?";
        return queryMulti(sql, Furn.class, "%" + name + "%", begin, pageSize);
    }
}
