package com.sxkj.de.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxkj.de.bean.ReceiveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao
 *
 * @author NewGr8Player
 */
@Mapper
@Repository
public interface ReceiveInfoDao extends BaseMapper<ReceiveInfo> {

}
