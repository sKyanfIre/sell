package com.zzz.sell.service.impl;

import com.zzz.sell.dao.ModuleActionDao;
import com.zzz.sell.pojo.SysModuleAction;
import com.zzz.sell.service.ModuleActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzz
 * @description
 * @date 2020/2/29
 */
@Service
public class ModuleActionSerivceImpl implements ModuleActionService {
    @Autowired
    private ModuleActionDao moduleActionDao;
    @Override
    public List<SysModuleAction> getActionByRole(String roleId) {
        return null;
    }

    @Override
    public List<SysModuleAction> getAllActions() {
        return moduleActionDao.findAllActions();
    }
}
