package com.tawe.crowd.service;

import com.tawe.crowd.entity.Menu;
import com.tawe.crowd.entity.MenuAll;

import java.util.List;

public interface MenuService {

    List<? extends Menu> getAll();
}
