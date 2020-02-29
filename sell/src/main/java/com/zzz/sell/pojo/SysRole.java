package com.zzz.sell.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author zzz
 * @description sys_role
 * @date 2020/2/29
 */
@Data
@ToString
public class SysRole {
    private String roleId;
    private String roleName;
    private String createTime;
    private String updateTime;
    private String isValid;
}
