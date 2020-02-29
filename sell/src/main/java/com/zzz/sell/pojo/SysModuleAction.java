package com.zzz.sell.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author zzz
 * @description sys_module_action
 * @date 2020/2/29
 */
@Data
@ToString
public class SysModuleAction {
    private String actionId;
    private String moduleId;
    private String actionName;
    private String actionUrl;
    private String createTime;
    private String updateTime;
    private String isValid;
}
