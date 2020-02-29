package com.zzz.sell.service;

import com.zzz.sell.pojo.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> getRolesByActionId(String actionId);
}
