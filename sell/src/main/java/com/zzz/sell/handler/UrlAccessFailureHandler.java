package com.zzz.sell.handler;

import com.zzz.sell.utils.HttpUtil;
import com.zzz.sell.contants.StatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author zzz
 * @description 自定义授权失败处理器
 * @date 2020/3/1
 */
@Component
public class UrlAccessFailureHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(200);
        PrintWriter pw = httpServletResponse.getWriter();
        HashMap<String,Object> result = new HashMap<>(1);
        result.put("RespCode",StatusCode.LOGIN_NOT_PERMIT);
        result.put("RespDesc",StatusCode.LOGIN_NOT_PERMIT_DETAIL);
        pw.write(HttpUtil.getJson(result));
        pw.flush();
        pw.close();
    }
}
