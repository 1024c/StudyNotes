package com.tawe.service.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tawe.common.utils.ResultEntity;
import com.tawe.service.cms.entity.CrmBanner;
import com.tawe.service.cms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author tawe
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/cms/crm-banner")
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("{page}/{limit}")
    public ResultEntity index(
            @PathVariable Long page,
            @PathVariable Long limit
    ) {
        Page<CrmBanner> pageParam = new Page<>(page, limit);
        crmBannerService.page(pageParam, null);
        return ResultEntity.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @GetMapping("get/{id}")
    public ResultEntity get(@PathVariable String id) {
        CrmBanner crmBanner = crmBannerService.getById(id);
        return ResultEntity.ok().data("item", crmBanner);
    }

    @PostMapping("save")
    public ResultEntity save(@RequestBody CrmBanner crmBanner) {
        boolean result = crmBannerService.save(crmBanner);
        return result ? ResultEntity.ok() : ResultEntity.error();
    }

    @DeleteMapping("remove/{id}")
    public ResultEntity remove(@PathVariable String id) {
        boolean result = crmBannerService.removeById(id);
        return result ? ResultEntity.ok() : ResultEntity.error();
    }
}

