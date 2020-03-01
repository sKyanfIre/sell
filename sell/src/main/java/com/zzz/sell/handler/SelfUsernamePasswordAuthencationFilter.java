package com.zzz.sell.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzz.sell.vo.LoginVo;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zzz
 * @description 自定义登录校验过滤器 支持form表单登录和json格式登录
 * @date 2020/3/2
 */
public class SelfUsernamePasswordAuthencationFilter extends UsernamePasswordAuthenticationFilter{
    private LoginVo bean = null;
    private LoginVo getValue(HttpServletRequest request){
        try {
            InputStream in = request.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            bean = objectMapper.readValue(in,LoginVo.class);
            return bean;
        } catch (IOException e) {
            logger.error("获取登录信息异常",e);
        }
        return null;
    }
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())
                || MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType())){
            //json方式登录
            bean = getValue(request);
        if (bean != null) {
            return bean.getUsername();
        }
    }
        //默认的表单方式登录
        return super.obtainUsername(request);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
            //json方式登录
            if(bean != null) {
                return bean.getUsername();
            }
        //默认的表单方式登录
        return super.obtainPassword(request);
    }

}
