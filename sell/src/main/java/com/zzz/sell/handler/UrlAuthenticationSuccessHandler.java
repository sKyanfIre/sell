package com.zzz.sell.handler;


import com.zzz.sell.contants.StatusCode;
import com.zzz.sell.utils.HttpUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zzz
 * @description 自定义登录成功处理器
 * @date 2020/2/28
 */
@Component
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setStatus(200);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        PrintWriter pw = httpServletResponse.getWriter();
        HashMap<String,Object> result = new HashMap<>(1);
        result.put("RespCode", StatusCode.SUCCESS);
        result.put("RespDesc", StatusCode.SUCCESS_DETAIL);
        pw.write(HttpUtil.getJson(result));
        pw.flush();
        pw.close();
    }
}
