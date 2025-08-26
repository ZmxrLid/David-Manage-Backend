package com.zmxrlid.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
@Getter
@Setter
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生id")
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;

    @ApiModelProperty("学生名字")
    private String studentName;

    @ApiModelProperty("学生性别")
    private String studentSex;

    @ApiModelProperty("学生母亲")
    private String studentMa;

    @ApiModelProperty("学生父亲")
    private String studentFa;

    @ApiModelProperty("学生亲属")
    private String studentOther;

    @ApiModelProperty("学生母亲电话")
    private String studentMatel;

    @ApiModelProperty("学生父亲电话")
    private String studentFatel;

    @ApiModelProperty("学生亲属电话")
    private String studentOthertel;

    @ApiModelProperty("学生所属老师")
    private Integer studentTeacherid;

    @ApiModelProperty("学生所属学科")
    private Integer studentClassid;

    @ApiModelProperty("学生入校时间")
    private LocalDateTime studentTime;

    @ApiModelProperty("学生所剩课时")
    private Integer studentHours;

    @ApiModelProperty("学生年龄")
    private String studentAge;

    @ApiModelProperty("学生状态")
    private Integer studentState;

    @ApiModelProperty("学生课程名字")
    private String studentClassname;

    @ApiModelProperty("学生老师名字")
    private String studentTeachername;

    @ApiModelProperty("校长id")
    private Integer studentMasterid;

    @ApiModelProperty("课时费")
    private Double studentClasscoll;

    @ApiModelProperty("总课时")
    private Integer studentZhours;



}
