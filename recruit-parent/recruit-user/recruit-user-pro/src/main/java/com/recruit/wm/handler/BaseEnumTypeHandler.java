package com.recruit.wm.handler;

import com.recruit.wm.common.BaseEnum;
import com.recruit.wm.enums.Education;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangm
 * @title: BaseEnumTypeHandler
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/616:57
 */
@NoArgsConstructor
@MappedTypes({Education.class})
public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {

    /**
     * 枚举的class
     */
    private Class<E> type;
    /**
     * 枚举的每个元素
     */
    private E[] enums;

    private Map<Object, E> enumMap;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     *
     * @param type 配置文件中设置的转换类
     */
    public BaseEnumTypeHandler(Class<E> type) {
        this.type = type;
        this.enums = type.getEnumConstants();
        enumMap = new HashMap<>(enums.length);
        for (E e : enums) {
            enumMap.put(Objects.toString(e.getValue()), e);
        }
    }

    /**
     * 用于定义设置参数时，该如何把Java类型的参数转换为对应的数据库类型；
     *
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
                                    JdbcType jdbcType) throws SQLException {
        /*
         * BaseTypeHandler已经帮我们做了parameter的null判断
         * 数据库存储的是枚举的值，所以我们这里使用 value ， 如果需要存储 name，可以自定义修改
         */
        if (jdbcType == null) {
            ps.setObject(i, parameter.getValue());
        } else {
            ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的Java类型
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String i = rs.getString(columnName);
        return rs.wasNull() ? null : enumMap.get(i);
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型；
     *
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String i = rs.getString(columnIndex);
        return rs.wasNull() ? null : enumMap.get(i);
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     *
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String i = cs.getString(columnIndex);
        return cs.wasNull() ? null : enumMap.get(i);
    }
}
