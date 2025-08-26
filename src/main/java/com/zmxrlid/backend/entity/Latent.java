package com.zmxrlid.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2025-08-02
 */
@Getter
@Setter
  @ApiModel(value = "Latent对象", description = "")
public class Latent implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("邀约id")
        @TableId(value = "latent_id", type = IdType.AUTO)
      private Integer latentId;

      @ApiModelProperty("邀约学生名字")
      private String latentStuname;

      @ApiModelProperty("邀约来源")
      private String latentFrom;

      @ApiModelProperty("邀约人")
      private String latentFromname;

      @ApiModelProperty("电话联系人")
      private String latentTelname;

      @ApiModelProperty("试听课老师")
      private String latentTeachername;

      @ApiModelProperty("约谈老师")
      private String latentTalkname;

      @ApiModelProperty("宣讲人")
      private String latentSpeech;

      @ApiModelProperty("最后操作记录人")
      private String latentEndpeoplename;

      @ApiModelProperty("最后操作记录人id")
      private Integer latentEndpeopleid;

      @ApiModelProperty("状态")
      private Integer latentState;

      @ApiModelProperty("备注")
      private String latentRemark;

      @ApiModelProperty("家长联系电话")
      private String latentTel;

      @ApiModelProperty("家长姓名")
      private String latentContact;

      @ApiModelProperty("邀约成功分成")
      private Double latentNum;

      @ApiModelProperty("性别")
      private String latentStusex;

      @ApiModelProperty("年龄")
      private Integer latentAge;

      @ApiModelProperty("预约课程")
      private String latentClass;

      @ApiModelProperty("删除")
      private Integer latentDelete;

      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ApiModelProperty("更新时间")
  private LocalDateTime latentTime;

  @ApiModelProperty("创建时间")
  private LocalDateTime latentYtime;

  @ApiModelProperty("成交时间")
  private LocalDateTime latentCtime;

  @ApiModelProperty("校长id")
  private Integer latentMasterid;

}
