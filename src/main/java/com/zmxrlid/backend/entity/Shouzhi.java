package com.zmxrlid.backend.entity;

import java.io.Serializable;
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
 * @since 2025-08-15
 */
@Getter
@Setter
  @ApiModel(value = "Shouzhi对象", description = "")
public class Shouzhi implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("收支id")
      @TableId(value = "shouzhi_id", type = IdType.AUTO)
        private Integer shouzhiId;

      @ApiModelProperty("收支校长id")
      private Integer shouzhiMasterid;

      @ApiModelProperty("收支绩效")
      private Double shouzhiJixiao;

      @ApiModelProperty("工资")
      private Double shouzhiGongzi;

      @ApiModelProperty("房租")
      private Double shouzhiFangzu;

      @ApiModelProperty("水")
      private Double shouzhiShui;

      @ApiModelProperty("支出")
      private Double shouzhiZhi;

      @ApiModelProperty("收入")
      private Double shouzhiShou;

      @ApiModelProperty("纯利润")
      private Double shouzhiNum;

      @ApiModelProperty("时间")
      private String shouzhiDate;

      @ApiModelProperty("备注")
      private String shouzhiRemake;

      @ApiModelProperty("电")
      private Double shouzhiDian;

      @ApiModelProperty("物业")
      private Double shouzhiWuye;

  @ApiModelProperty("状态")
  private Integer shouzhiState;

}
