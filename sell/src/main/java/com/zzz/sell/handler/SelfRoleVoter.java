package com.zzz.sell.handler;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author zzz
 * @description 自定义权限投票类
 * @date 2020/2/29
 */
public class SelfRoleVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     *
     * @param authentication
     * @param o
     * @param collection
     * @return 1-允许访问 -1 -拒绝访问
     */
    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        if(authentication == null){
            return -1;
        }
        //从身份实体中取出角色
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator authoritiesItor = authorities.iterator();
        if(authoritiesItor.hasNext()){
            GrantedAuthority grantedAuthority = (GrantedAuthority)authoritiesItor.next();
            //当前请求url允许的角色
            Iterator<ConfigAttribute> configAttributeIterator = collection.iterator();
            while(configAttributeIterator.hasNext()){
                ConfigAttribute configAttribute = configAttributeIterator.next();
                if(configAttribute.getAttribute().equals(grantedAuthority.getAuthority())){
                    //授权通过
                    return 1;
                }
            }
        }
        return -1;
    }
}
