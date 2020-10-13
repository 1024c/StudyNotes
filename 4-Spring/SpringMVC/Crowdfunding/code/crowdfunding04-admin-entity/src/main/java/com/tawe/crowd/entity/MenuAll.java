package com.tawe.crowd.entity;

import java.util.ArrayList;
import java.util.List;

public class MenuAll extends Menu {

    private Boolean open = true;
    private List<Menu> children = new ArrayList<>();

    public MenuAll(Menu menu) {
        super();
        this.setId(menu.getId());
        this.setPid(menu.getPid());
        this.setMenuName(menu.getMenuName());
        this.setUrl(menu.getUrl());
        this.setIcon(menu.getIcon());
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
