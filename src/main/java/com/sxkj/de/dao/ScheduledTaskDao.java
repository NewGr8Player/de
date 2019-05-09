package com.sxkj.de.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxkj.de.bean.ScheduledTask;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 定时任务Dao
 *
 * @author NewGr8Player
 */
@Mapper
@Repository
public interface ScheduledTaskDao extends BaseMapper<ScheduledTask> {

}
