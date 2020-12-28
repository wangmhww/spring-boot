package com.recruit.wm.security01.service;

import com.recruit.wm.security01.bean.Permission;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 9:59 2020/12/28
 * @since 1.2.0
 */
public interface IPermissionService {
    public List<Permission> selectById(BigInteger id);
}
