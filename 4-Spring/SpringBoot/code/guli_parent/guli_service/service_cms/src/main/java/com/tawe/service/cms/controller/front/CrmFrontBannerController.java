package com.tawe.service.cms.controller.front;

import com.tawe.common.utils.ResultEntity;
import com.tawe.service.cms.entity.CrmBanner;
import com.tawe.service.cms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CrmFrontBannerController
 * @Description TODO
 * @Author davidt
 * @Date 12/23/2020 5:55 PM
 * @Version 1.0
 **/

@RestController
@CrossOrigin
@RequestMapping("/cms/front/crm-banner")
public class CrmFrontBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    @Cacheable(value = "banner", key = "'indexList'")
    public ResultEntity getAllBanner() {

        List<CrmBanner> banners = crmBannerService.list(null);
        return ResultEntity.ok().data("items", banners);
    }

}
