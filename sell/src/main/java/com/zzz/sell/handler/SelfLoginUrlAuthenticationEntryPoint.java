package com.zzz.sell.handler;

import com.zzz.sell.contants.StatusCode;
import com.zzz.sell.utils.HttpUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zzz
 * @description 自定义身份认证失败处理
 * @date 2020/2/28
 */
public class SelfLoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(200);
        PrintWriter pw = httpServletResponse.getWriter();
        HashMap<String,Object> result = new HashMap<>(1);
        result.put("RespCode", StatusCode.NO_AUTH);
        result.put("RespDesc",StatusCode.NO_AUTH_DETAIL);
        pw.write(HttpUtil.getJson(result));
        pw.flush();
        pw.close();
    }
}
