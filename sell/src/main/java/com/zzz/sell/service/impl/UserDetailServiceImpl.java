package com.zzz.sell.service.impl;

import com.zzz.sell.dao.UserDao;
import com.zzz.sell.pojo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zzz
 * @description 系统用户相关操作
 * @date 2020/2/28
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    /**
     * 自定义springsecurity用户校验
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userDao.findUserByUserName(s);
        if(user == null){
            logger.info("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
