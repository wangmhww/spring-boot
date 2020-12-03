package com.recruit.wm.model;

import lombok.*;
import lombok.extern.log4j.Log4j;

/**
 * @author wangm
 * @title: User
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/321:45
 */
@Log4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    public String name;
    public int age;
}
