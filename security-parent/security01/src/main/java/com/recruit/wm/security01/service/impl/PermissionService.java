package com.recruit.wm.security01.service.impl;

import com.recruit.wm.security01.bean.Permission;
import com.recruit.wm.security01.dao.PermissionDao;
import com.recruit.wm.security01.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author wangmingf
 * @Date Create in 9:59 2020/12/28
 * @since 1.2.0
 */
@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> selectById(BigInteger id) {
        return permissionDao.selectById(id);
    }
}
