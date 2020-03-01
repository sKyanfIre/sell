package com.zzz.sell.utils;

import com.zzz.sell.contants.TokenContants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @author zzz
 * @description token工具集
 * @date 2020/2/28
 */
public class TokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    public static boolean setToken(HttpSession session,String token){
        try {
            session.setAttribute(TokenContants.TOKEN_LOGIN, token);
        }catch (Exception e){
            logger.error("保存登录信息失败",e);
            return false;
        }
        return true;
    }
}
