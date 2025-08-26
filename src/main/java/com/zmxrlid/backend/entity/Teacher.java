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
  @ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("老师id")
        @TableId(value = "teacher_id", type = IdType.AUTO)
      private Integer teacherId;

      @ApiModelProperty("老师账号")
      private String teacherName;

      @ApiModelProperty("老师密码")
      private String teacherPassword;

  @ApiModelProperty("老师电话")
  private String teacherTel;

      @ApiModelProperty("老师所属校长")
      private Integer teacherMasterid;

  @ApiModelProperty("老师状态")
  private Integer teacherState;

    @ApiModelProperty("底薪")
    private Double teacherNum;

    @ApiModelProperty("入职时间")
    private String teacherTime;

  @ApiModelProperty("保险状态")
  private Integer teacherBaostate;

  @ApiModelProperty("保险金额")
  private Double teacherBaonum;

  @ApiModelProperty("保险公司承担")
  private Double teacherBaogong;

  @ApiModelProperty("绩效区间")
  private Integer teacherJixiao;

  @ApiModelProperty("全勤奖")
  private Double teacherKaoqin;

  @ApiModelProperty("持有股份")
  private Integer teacherGu;

}
