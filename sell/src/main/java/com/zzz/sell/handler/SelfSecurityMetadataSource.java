package com.zzz.sell.handler;

import com.zzz.sell.pojo.SysModuleAction;
import com.zzz.sell.pojo.SysRole;
import com.zzz.sell.service.ModuleActionService;
import com.zzz.sell.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zzz
 * @description 自定义权限获取类
 * @date 2020/2/29
 */
@Component
public class SelfSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final Logger logger = LoggerFactory.getLogger(SelfSecurityMetadataSource.class);
    @Autowired
    private ModuleActionService moduleActionService;
    @Autowired
    private RoleService roleService;
    private List<SysModuleAction> actions;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet();
      return allAttributes;
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        long start = System.currentTimeMillis();
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //查询所有module_action
        actions = moduleActionService.getAllActions();
        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        Iterator<SysModuleAction> iterator = this.actions.iterator();
        while(iterator.hasNext()){
            //遍历数据库中保存的action
            SysModuleAction action = iterator.next();
            //找到与当前请求url匹配的action
            if(new AntPathRequestMatcher(action.getActionUrl()).matches(request)){
                //查找action对应的role列表
                List<SysRole> roles = roleService.getRolesByActionId(action.getActionId());
                for(SysRole role : roles){
                    ConfigAttribute configAttribute = new SecurityConfig(role.getRoleId());
                    configAttributes.add(configAttribute);
                }
                //未查询到权限，设置默认权限
                if(configAttributes.isEmpty()) {
                    configAttributes.add(new SecurityConfig("-1"));
                }
                logger.info("加载权限资源耗时" + (System.currentTimeMillis() - start) + "ms");
                return configAttributes;
            }
        }
        logger.info("加载权限资源耗时" + (System.currentTimeMillis() - start) + "ms");
        return null;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

