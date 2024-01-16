package com.charlie.furns.service.impl;

import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.dao.impl.FurnDAOImpl;
import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
import com.charlie.furns.service.FurnService;

import java.util.List;

public class FurnServiceImpl implements FurnService {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public List<Furn> queryFurns() {
        return furnDAO.queryFurns();
    }

    @Override
    public boolean addFurn(Furn furn) {
        return furnDAO.addFurn(furn) == 1;
    }

    @Override
    public boolean deleteFurnById(int id) {
        return furnDAO.deleteFurnById(id) == 1;
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    @Override
    public boolean updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn) == 1;
    }

    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
        // 先创建一个page对象，然后根据实际情况填充属性
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);                     // 要显示第几页
        page.setPageSize(pageSize);                 // 每页显示的数据量
        int totalRow = furnDAO.getTotalRow();
        page.setTotalRow(totalRow);                 // 一共有多少行数据记录

        // pageTotalCount 要显示的页数
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageNo != 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);     // 一共可以显示多少页

        // begin = (第几页 - 1) * 每页显示的数据
        int begin = (pageNo - 1) * pageSize;
        page.setItems(furnDAO.getPageItems(begin, pageSize));
        return page;
    }

    @Override
    public Page<Furn> pageByName(String name, int pageNo, int pageSize) {
        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDAO.getTotalRowByName(name);
        page.setTotalRow(totalRow);
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize != 0) {
            pageTotalCount += 1;
        }
        page.setPageTotalCount(pageTotalCount);
        int begin = (pageNo - 1) * pageSize;
        page.setItems(furnDAO.getPageItemsByName(name, begin, pageSize));
        return page;
    }
}
