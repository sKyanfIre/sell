package com.zzz.sell.handler;

import com.zzz.sell.contants.StatusCode;
import com.zzz.sell.Utils.HttpUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zzz
 * @description 自定义登录失败处理器
 * @date 2020/2/28
 */
@Component
public class UrlAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(401);
        PrintWriter pw = httpServletResponse.getWriter();
        HashMap<String,Object> result = new HashMap<>(1);
        if(e instanceof BadCredentialsException || e instanceof UsernameNotFoundException){
            result.put("RespCode",StatusCode.LOGIN_ERR_PASSWORD);
            result.put("RespDesc",StatusCode.LOGIN_ERR_PASSWORD_DETAIL);
        }else{
            result.put("RespCode",StatusCode.LOGIN_FAILURE);
            result.put("RespDesc",StatusCode.LOGIN_FAIURE_DETAIL);
        }

        pw.write(HttpUtil.getJson(result));
        pw.flush();
        pw.close();
    }
}
