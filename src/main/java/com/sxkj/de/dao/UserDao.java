package com.sxkj.de.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxkj.de.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao extends BaseMapper<User> {
}
