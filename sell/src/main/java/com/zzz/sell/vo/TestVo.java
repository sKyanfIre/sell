package com.zzz.sell.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zzz
 * @description testvo
 * @date 2020/3/1
 */
@ApiModel(value = "测试类",description = "测试一下apimodel")
@Data
@ToString
public class TestVo {
    @ApiModelProperty(value="姓名e",example = "zty")
    private String name;
    @ApiModelProperty(value = "密码",example = "123456")
    private String password;
    @ApiModelProperty(value = "年龄",example = "18")
    private Integer age;
    @ApiModelProperty(value = "女朋友们",example = "xxx、xxxx")
    private ArrayList<String> girlfriends;
    @ApiModelProperty(example = "好多房子")
    private String[] homes;
    private Map<String,Object> map;
}
