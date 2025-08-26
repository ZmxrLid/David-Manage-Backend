package com.zmxrlid.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2025-07-30
 */
@Getter
@Setter
  @ApiModel(value = "Taxes对象", description = "")
public class Taxes implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("消课id")
        @TableId(value = "taxes_id", type = IdType.AUTO)
      private Integer taxesId;

      @ApiModelProperty("申请时间")
      private LocalDateTime taxesTime;

      @ApiModelProperty("消课备注")
      private String taxesRemark;

      @ApiModelProperty("申请老师id")
      private Integer taxesTeacherid;

      @ApiModelProperty("申请老师姓名")
      private String taxesTeachername;

      @ApiModelProperty("消课校长id")
      private Integer taxesMasterid;

      @ApiModelProperty("消课学生id组")
      private String taxesStuidlist;

      @ApiModelProperty("消课学生姓名组")
      private String taxesStunamelist;

      @ApiModelProperty("消课班级id")
      private Integer taxesClassid;

      @ApiModelProperty("消课班级名字")
      private String taxesClassname;

  @ApiModelProperty("状态id")
  private Integer taxesState;

  @ApiModelProperty("消课日期")
  private String taxesDatetime;


}
