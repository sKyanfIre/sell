package com.zzz.sell.dao;

import com.zzz.sell.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
     SysUser findUserByUserName(String username);
}
