package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 职工表
 * @Author 张继升
 * @Date 2023/7/21 18:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "employee")
@ApiModel(value="职工表单对象")
public class Employee  implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @ApiModelProperty(value = "id",required = true,example = "id主键，后端自动赋值")
    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using=ToStringSerializer.class)
    private Long id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "职工姓名",required = true)
    @Excel(sort = 1,name = "职工姓名" )
    private String empName;

    /**
     * 性别：   采用字典中的
     */
    @Excel(sort = 2,name = "性别",dictType = "sys_user_sex")
    private Integer sex;
    /**
     * 年龄
     */
    @Excel(sort = 3,name = "年龄")
    private Integer age;
    /**
     * 部门   采用字典中的dept_name
     */
    @Excel(sort = 4,name = "部门",dictType = "dept_name")
    private Integer deptName;
    /**
     * 学历   采用字典中的emp_degree_name
     */
    @Excel(sort = 5,name = "学历",dictType = "emp_degree_name")
    private Integer empDegreeName;
    /**
     * 是否删除标志：0否  ，1是
     */
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;
    /**
     * 创建人id
     */
    @JsonSerialize(using=ToStringSerializer.class)
    private Long createId;
    /**
     * 创建人姓名
     */
    private String createBy;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 修改人id
     */
    @JsonSerialize(using=ToStringSerializer.class)
    private Long updateId;
    /**
     * 修改人id
     */
    private String updateBy;
    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;



}
