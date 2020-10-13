package com.tawe.crowd.service;

import com.tawe.crowd.entity.Menu;

import java.util.List;

public interface MenuService {

    List<? extends Menu> getAll();

    int saveMenu(Menu menu);
}
