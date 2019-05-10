package com.sxkj.de.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sxkj.de.bean.AreaContrast;
import com.sxkj.de.common.page.Page;
import com.sxkj.de.service.AreaContrastService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 行政区划对照接口
 *
 * @author lidan
 */
@Api(value = "查询接口")
@RestController
@RequestMapping("/api/areaContrast")
@Slf4j
public class AreaContrastApi {
    @Autowired
    private AreaContrastService areaContrastService;

    @RequestMapping(path = "/queryList", method = {RequestMethod.GET, RequestMethod.POST})
    public IPage<Map<String, Object>> roleListQuery(Page<AreaContrast> areaContrastPage, AreaContrast areaContrast) {
        IPage<Map<String, Object>> mapIPage;
        try {
            mapIPage = areaContrastService.selectAreaContrastListPage(areaContrastPage, areaContrast);
        } catch (Exception ex) {
            log.error("AreaContrastApi接口,roleListQuery方法错误：{}", ex.getMessage());
            mapIPage = null;
        }
        return mapIPage;
    }
}
