package com.recruit.wm.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangm
 * @title: User
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/321:45
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    public String name;
    public int age;
}
