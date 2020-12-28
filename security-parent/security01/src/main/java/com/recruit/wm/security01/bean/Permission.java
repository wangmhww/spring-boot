package com.recruit.wm.security01.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author wangmingf
 * @Date Create in 9:50 2020/12/28
 * @since 1.2.0
 */

@Data
@Setter
@Getter
public class Permission {
    private BigInteger id;
    private BigInteger parentId;

    private String name;
    private String enName;
    private String url;
    private String description;
    private Date created;
    private Date updated;
}
