package com.zmxrlid.backend.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2025-08-13
 */
@Getter
@Setter
  @ApiModel(value = "Kaoqin对象", description = "")
public class Kaoqin implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("考勤id")
      @TableId(value = "kaoqin_id", type = IdType.AUTO)
        private Integer kaoqinId;

      @ApiModelProperty("考勤校长id")
      private Integer kaoqinMasterid;

      @ApiModelProperty("考勤老师id")
      private Integer kaoqinTeacherid;

      @ApiModelProperty("考勤时间")
      private String kaoqinDate;

      @ApiModelProperty("上班天数")
      private Integer kaoqinDays;

      @ApiModelProperty("迟到早退10分钟内")
      private Integer kaoqinTentime;

      @ApiModelProperty("迟到早退一小时内")
      private Integer kaoqinOnehours;

      @ApiModelProperty("迟到早退两小时内")
      private Integer kaoqinTwohours;

      @ApiModelProperty("迟到早退三小时内")
      private Integer kaoqinThreehours;

      @ApiModelProperty("请假半天")
      private Integer kaoqinBantian;

      @ApiModelProperty("请假一天")
      private Integer kaoqinQingjia;

      @ApiModelProperty("应该上班的天数")
      private Integer kaoqinYing;

      @ApiModelProperty("考勤金额")
      private Double kaoqinNum;

      @ApiModelProperty("老师底薪")
      private Double kaoqinTeachernum;

      @ApiModelProperty("全勤奖")
      private Double kaoqinKaoqinnum;

  @ApiModelProperty("考勤老师")
  private String kaoqinTeachername;

  @ApiModelProperty("状态")
  private Integer kaoqinState;


}
