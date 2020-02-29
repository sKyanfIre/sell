package com.zzz.sell.dao;

import com.zzz.sell.pojo.SysModuleAction;
import com.zzz.sell.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModuleActionDao {

    List<SysRole> findRolesByActionUrl(String actionUrl);

    List<SysModuleAction> findAllActions();
}
