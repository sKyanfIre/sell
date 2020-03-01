package com.zzz.sell.controller;

import com.zzz.sell.vo.TestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * @author zzz
 * @description
 * @date 2020/2/28
 */
@Api(description = "session相关操作")
@RestController
public class SessionController {
    @ApiOperation(value = "设置session信息",notes = "默认设置session的key为token")
    @RequestMapping(value = "/setSession",method = RequestMethod.GET)
    public TestVo setSession(@ApiIgnore HttpSession session, @ApiParam(name = "key",value = "不知道是什么") String key){
        session.setAttribute("token","123456");
        return new TestVo();
    }
    @RequestMapping(value = "/getSession",method = RequestMethod.GET)
    public String getSession(@ApiIgnore HttpSession session,@ApiParam TestVo test){
       return  session.getAttribute("token").toString();
    }
}
