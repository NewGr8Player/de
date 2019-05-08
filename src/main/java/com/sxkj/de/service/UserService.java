package com.sxkj.de.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sxkj.de.bean.User;
import com.sxkj.de.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService extends ServiceImpl<UserDao, User> {

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return
     */
    public User findByUserName(String userName){
        return baseMapper.selectOne(new QueryWrapper<>(new User().username(userName)));
    }

}
