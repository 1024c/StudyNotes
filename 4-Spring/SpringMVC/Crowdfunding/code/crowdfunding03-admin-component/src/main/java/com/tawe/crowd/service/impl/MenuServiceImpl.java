package com.tawe.crowd.service.impl;

import com.tawe.crowd.dao.MenuMapper;
import com.tawe.crowd.entity.Menu;
import com.tawe.crowd.entity.MenuExample;
import com.tawe.crowd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<? extends Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public int saveMenu(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int removeMenu(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }
}
