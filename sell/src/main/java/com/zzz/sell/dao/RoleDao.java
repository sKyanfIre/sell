package com.zzz.sell.dao;

import com.zzz.sell.pojo.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzz
 * @description
 * @date 2020/2/29
 */
@Mapper
public interface RoleDao {

    List<SysRole> findRolesByActionId(String actionId);
}
