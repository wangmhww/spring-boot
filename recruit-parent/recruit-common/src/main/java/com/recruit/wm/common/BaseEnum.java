package com.recruit.wm.common;

/**
 * @author wangm
 * @title: BaseEnum
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/616:38
 */
public interface BaseEnum<E extends Enum<?>, T> {
    /**
     * 获取枚举值
     *
     * @return 枚举值
     */
    T getValue();
}
