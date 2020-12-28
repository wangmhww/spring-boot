package com.wm.dao;

import com.wm.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangm
 * @title: PermissionDao
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:34
 */
@Mapper
public interface PermissionDao {
    public List<Permission> selectById(BigInteger id);
}
