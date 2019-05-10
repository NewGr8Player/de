package com.sxkj.de.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxkj.de.bean.AreaContrast;
import com.sxkj.de.dao.AreaContrastDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 用户Service
 *
 * @author NewGr8Player
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class AreaContrastService extends ServiceImpl<AreaContrastDao, AreaContrast> {

    @Autowired
    private AreaContrastDao areaContrastDao;

    public IPage<Map<String, Object>> selectAreaContrastListPage(Page<AreaContrast> areaContrastPage, AreaContrast areaContrast) throws Exception {

        QueryWrapper<AreaContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("1=1");
        if(StringUtils.isNotBlank(areaContrast.getName())){
            queryWrapper.apply("name like '%"+areaContrast.getName()+"%'");
        }
        if(StringUtils.isNotBlank(areaContrast.getLocalName())){
            queryWrapper.apply("local_name like '%"+areaContrast.getLocalName()+"%'");
        }

        return baseMapper.selectMapsPage(areaContrastPage, queryWrapper);

    }

    public IPage<AreaContrast> selectRoleListPage(IPage<AreaContrast> areaContrastPage, AreaContrast areaContrast) {
        QueryWrapper<AreaContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("1=1");
        return baseMapper.selectPage(areaContrastPage, queryWrapper);
    }
}
