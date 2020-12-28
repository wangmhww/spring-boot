package com.wm.service.impl;

import com.wm.bean.Permission;
import com.wm.dao.PermissionDao;
import com.wm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangm
 * @title: PermissionService
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:56
 */
@Service
public class PermissionService implements IPermissionService {

    @Autowired
    public PermissionDao permissionDao;

    @Override
    public List<Permission> selectByUserId(BigInteger id) {
        return permissionDao.selectById(id);
    }
}
