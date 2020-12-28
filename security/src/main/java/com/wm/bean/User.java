package com.wm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author wangm
 * @title: User
 * @projectName security
 * @description: TODO
 * @date 2020/12/2621:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    private BigInteger id;

    private String userName;

    private String password;

    private String phone;

    private Date created;

    private Date updated;
}
