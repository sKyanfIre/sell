package com.zzz.sell.service.impl;

import com.zzz.sell.dao.RoleDao;
import com.zzz.sell.pojo.SysRole;
import com.zzz.sell.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzz
 * @description
 * @date 2020/2/29
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<SysRole> getRolesByActionId(String actionId) {
        return roleDao.findRolesByActionId(actionId);
    }
}
