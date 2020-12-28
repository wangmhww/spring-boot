package com.wm.service;

import com.wm.bean.Permission;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangm
 * @title: IPermissionService
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:55
 */
public interface IPermissionService {

    public List<Permission> selectByUserId(BigInteger id);
}
