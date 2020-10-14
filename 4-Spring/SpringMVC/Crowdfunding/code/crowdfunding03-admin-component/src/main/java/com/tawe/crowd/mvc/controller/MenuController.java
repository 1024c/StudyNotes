package com.tawe.crowd.mvc.controller;

import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Menu;
import com.tawe.crowd.entity.MenuAll;
import com.tawe.crowd.service.MenuService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/page.html")
    public ModelAndView getMenu() {
        return new ModelAndView("forward:/menu/to/main/page.html");
    }

    @RequestMapping("/menu/remove.json")
    public ResultEntity<String> removeMenu(Integer id) {
        int col = menuService.removeMenu(id);
        if (col == 1) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }
    }

    @RequestMapping("/menu/update.json")
    public ResultEntity<String> updateMenu(@RequestBody Menu menu) {
        int col = menuService.updateMenu(menu);
        if (col == 1) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }
    }

    @RequestMapping("/menu/get/whole/tree.json")
    public ResultEntity<MenuAll> getWholeTreeView() {
        // 1. 查询全部的 Menu 对象
        List<? extends Menu> menus = menuService.getAll();
        // 2. 声明一个变量用于存储找到的根节点
        // 2.1. 声明一个 MenuAll List 存储 MenuAll 集合
        MenuAll root = null;
        List<MenuAll> menuAlls = new ArrayList<>();
        // 3. 创建 Map 对象用来存储 Id 和 Menu 对象的对应关系
        Map<Integer, MenuAll> menuMap = new HashMap<>();
        // 4. 遍历 menuList 填充 menuMap
        for (Menu menu : menus) {
            MenuAll menuAll = new MenuAll(menu);
            menuAlls.add(menuAll);
            menuMap.put(menu.getId(), menuAll);
        }
        // 5. 再次遍历 menuList 查找根节点
        for (MenuAll menuAll : menuAlls) {
            // 6. 获取 menu 对象的 pid 属性值
            Integer pid = menuAll.getPid();

            // 7. 如果 pid 为空, 则为父节点
            if (pid == null) {
                root = menuAll;
            } else {
                // 8. 如果 pid 不为空,则根据 pid 将 menu 对象加入到 children 集合中
                menuMap.get(pid).getChildren().add(menuAll);
            }
        }
        // 9. 返回根节点即为整个数结构
        return ResultEntity.succeededWithData(root);
    }

    @RequestMapping("/menu/save.json")
    public ResultEntity<String> saveMenu(Menu menu) {
        int col = menuService.saveMenu(menu);
        if (col == 1) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }
    }
}
