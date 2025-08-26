package com.zmxrlid.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
  @ApiModel(value = "Salary对象", description = "")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("薪资id")
        @TableId(value = "salary_id", type = IdType.AUTO)
      private Integer salaryId;

      @ApiModelProperty("薪资所属校长")
      private Integer salaryMasterid;

      @ApiModelProperty("老师名字")
      private Integer salaryTeacherid;

      @ApiModelProperty("老师id")
      private String salaryTeachername;

      @ApiModelProperty("老师底薪")
      private Double salaryDixin;

      @ApiModelProperty("老师考勤")
      private Double salaryKaoqin;

      @ApiModelProperty("老师绩效")
      private Double salaryJixiao;

      @ApiModelProperty("邀约提成")
      private Double salaryTicheng;

      @ApiModelProperty("保险")
      private Double salaryBaoxian;

      @ApiModelProperty("工龄")
      private Integer salaryGongling;

      @ApiModelProperty("工龄奖金")
      private Double salaryGongsalary;

      @ApiModelProperty("股份分红")
      private Double salaryGufen;

      @ApiModelProperty("其他奖罚")
      private Double salaryExtra;

      @ApiModelProperty("总工资")
      private Double salaryNum;

      @ApiModelProperty("备注")
      private String salaryRemark;

      @ApiModelProperty("时间")
      private String salaryDate;

      @ApiModelProperty("绩效奖金")
      private Double salaryJixiaonum;

    @ApiModelProperty("实发工资")
    private Double salaryEndnum;

  @ApiModelProperty("状态")
  private Integer salaryState;


}
