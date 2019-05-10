package com.sxkj.de.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxkj.de.bean.AreaContrast;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao
 *
 * @author NewGr8Player
 */
@Mapper
@Repository
public interface AreaContrastDao  extends BaseMapper<AreaContrast> {

}
