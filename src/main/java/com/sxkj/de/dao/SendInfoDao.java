package com.sxkj.de.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxkj.de.bean.ReceiveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户Dao
 *
 * @author NewGr8Player
 */
@Mapper
@Repository
public interface SendInfoDao {

    @Select("select count(0) num from de_send_info where dispose_code = #{disposeCode}")
    Integer searchNumByDisposeCode(String disposeCode);

    @Select("SELECT DATE(create_date) createDate,COUNT(create_date) num FROM de_send_info WHERE dispose_code = '200' AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= create_date GROUP BY DATE(create_date)")
    List<Map<String, Object>> searchSendSuncessNum();

    @Select("SELECT DATE(create_date) createDate,COUNT(create_date) num FROM de_send_info WHERE dispose_code <> '200' AND DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= create_date GROUP BY DATE(create_date)")
    List<Map<String, Object>> searchSendErrorNum();

}
