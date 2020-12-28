package com.wm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author wangm
 * @title: Permission
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Permission implements Serializable {
    private BigInteger id;

    private String parentId;

    private String name;

    private String enName;

    private String url;

    private Date created;

    private Date updated;

}
