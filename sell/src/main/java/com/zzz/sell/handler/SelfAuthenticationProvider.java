package com.zzz.sell.handler;

import com.zzz.sell.service.impl.UserDetailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zzz
 * @description 自定义登录认证
 * @date 2020/2/28
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private UserDetailServiceImpl userDetailService;
    private static final Logger logger = LoggerFactory.getLogger(SelfAuthenticationProvider.class);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());
        UserDetails user = userDetailService.loadUserByUsername(username);
        if(password.equals(user.getPassword())){
            return new UsernamePasswordAuthenticationToken(username,password,user.getAuthorities());
        }else{
           logger.info("账号:" + username +"登录失败，密码错误");
           throw new BadCredentialsException("密码错误");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
