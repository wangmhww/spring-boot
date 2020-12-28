package com.recruit.wm.security01.dao;

import com.recruit.wm.security01.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 9:55 2020/12/28
 * @since 1.2.0
 */
@Mapper
public interface PermissionDao {
    public List<Permission> selectById(BigInteger id);
}
