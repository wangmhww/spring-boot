package com.recruit.wm.security01.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author wangmingf
 * @Date Create in 9:47 2020/12/28
 * @since 1.2.0
 */
@Data
@Getter
@Setter
public class User {
    private BigInteger id;

    private String userName;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;
}
