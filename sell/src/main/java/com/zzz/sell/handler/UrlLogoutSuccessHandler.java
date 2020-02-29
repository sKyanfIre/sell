package com.zzz.sell.handler;

import com.zzz.sell.contants.StatusCode;
import com.zzz.sell.Utils.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zzz
 * @description 自定义注销处理器
 * @date 2020/2/28
 */
@Component
public class UrlLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(200);
        PrintWriter pw = httpServletResponse.getWriter();
        HashMap<String,Object> result = new HashMap<>(1);
        result.put("RespCode", StatusCode.SUCCESS);
        result.put("RespDesc",StatusCode.SUCCESS_DETAIL);
        pw.write(HttpUtil.getJson(result));
        pw.flush();
        pw.close();
    }
}
