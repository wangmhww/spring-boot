package com.wm.springbootdemoone.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author wangmingf
 * @Date Create in 11:28 2020/10/29
 * @since 1.2.0
 */
@Data
@NoArgsConstructor
public class User {
    private String userName;

    private int age;

    private int regend;

}
