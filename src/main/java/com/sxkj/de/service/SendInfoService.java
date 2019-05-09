package com.sxkj.de.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxkj.de.bean.User;
import com.sxkj.de.dao.SendInfoDao;
import com.sxkj.de.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户Service
 *
 * @author NewGr8Player
 */
@Service
@Transactional(readOnly = true)
public class SendInfoService  {

    @Autowired
    private SendInfoDao sendInfoDao;

    /**
     * 根据事件处理状态码（前置机返回），查询成功数量
     * @param disposeCode
     * @return
     */
    public Integer searchNumByDisposeCode(String disposeCode) {
        return sendInfoDao.searchNumByDisposeCode(disposeCode);
    }

    /**
     * 最近7天上传成功的dat数量
     * @return
     */
    public List<Map<String, Object>> searchSendSuncessNum() {
        return sendInfoDao.searchSendSuncessNum();
    }

    /**
     * 上最近7天上传失败的dat数量
     * @return
     */
    public List<Map<String, Object>> searchSendErrorNum() {
        return sendInfoDao.searchSendErrorNum();
    }

}
