package com.zzz.sell.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author zzz
 * @description
 * @date 2020/2/28
 */
@RestController
public class SessionController {
    @RequestMapping(value = "/setSession",method = RequestMethod.GET)
    public void setSession(HttpSession session){
        session.setAttribute("token","123456");
    }
    @RequestMapping(value = "/getSession",method = RequestMethod.GET)
    public String getSession(HttpSession session){
       return  session.getAttribute("token").toString();
    }
}
