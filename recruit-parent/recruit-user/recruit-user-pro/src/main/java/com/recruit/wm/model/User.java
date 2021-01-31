package com.recruit.wm.model;

import com.recruit.wm.enums.Education;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

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
@ApiModel(value = "user", description = "用户实体类")
public class User implements Serializable {
    @ApiModelProperty(value = "用户Id", name = "name",dataType = "Integer")
    public int id;

    @ApiModelProperty(value = "用户姓名", name = "name",dataType = "String",required = true)
    public String name;

    @ApiModelProperty(value = "年龄", name = "age",dataType = "Integer",required = true)
    public int age;

    @ApiModelProperty(value = "用户性别", name = "sex",dataType = "Integer",required = true)
    public int sex;

    @ApiModelProperty(value = "国籍", name = "nationality",dataType = "String",required = true)
    public String nationality;

    @ApiModelProperty(value = "学历", name = "education",dataType = "Integer",required = true)
    public Education education;

    @ApiModelProperty(value = "专业", name = "speciality",dataType = "Integer",required = true)
    public int speciality;

    @ApiModelProperty(value = "手机号码", name = "phone",dataType = "String",required = true)
    public String phone;

    @ApiModelProperty(value = "职业", name = "occupation",dataType = "Integer",required = true)
    public int occupation;

    @ApiModelProperty(value = "民族", name = "nation",dataType = "Integer",required = true)
    public int nation;

    @ApiModelProperty(value = "发证机关", name = "issue",dataType = "String",required = true)
    public String issue;

    @ApiModelProperty(value = "地址", name = "address",dataType = "String",required = true)
    public String address;

}
