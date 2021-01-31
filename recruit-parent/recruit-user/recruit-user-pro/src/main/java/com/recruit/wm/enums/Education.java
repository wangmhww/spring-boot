package com.recruit.wm.enums;

import com.recruit.wm.common.BaseEnum;

/**
 * @author wangm
 * @title: Education
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/612:22
 */
public enum Education implements BaseEnum<Education,Integer> {
    PRIMARY_SCHOOL(1),
    JUNIOR_MIDDLE_SCHOOL(2),
    SECONDARY_SPECIALIZED_SCHOOL(3),
    JUNIOR_COLLEGE(4),
    UNDERGRADUATE(5),
    MASTER(6),
    DOCTOR(7);

    public int value;

    Education(int i) {
        this.value = i;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
