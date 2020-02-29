package com.zzz.sell.service;

import com.zzz.sell.pojo.SysModuleAction;
import java.util.List;

/**
 * @author zzz
 * @description 角色权限管理
 * @date 2020/2/29
 */

public interface ModuleActionService {
     List<SysModuleAction> getActionByRole(String roleId);
     List<SysModuleAction> getAllActions();
}
