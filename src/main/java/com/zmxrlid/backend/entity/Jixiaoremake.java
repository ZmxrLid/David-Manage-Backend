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
 * @since 2025-09-06
 */
@Getter
@Setter
  @ApiModel(value = "Jixiaoremake对象", description = "")
public class Jixiaoremake implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("绩效详情id")
        @TableId(value = "jixiaoremake_id", type = IdType.AUTO)
      private Integer jixiaoremakeId;

      @ApiModelProperty("绩效详情老师名字")
      private String jixiaoremakeTeachername;

      @ApiModelProperty("绩效详情老师id")
      private Integer jixiaoremakeTeacherid;

      @ApiModelProperty("绩效详情校长id")
      private Integer jixiaoremakeMasterid;

      @ApiModelProperty("绩效详情上课数")
      private Integer jixiaoremakeClassnum;

      @ApiModelProperty("绩效详情上课学生数")
      private Integer jixiaoremakeStudentnum;

      @ApiModelProperty("绩效详情扣除绩效")
      private Double jixiaoremakeKouchu;

      @ApiModelProperty("绩效详情备注")
      private String jixiaoremakeRemake;

      @ApiModelProperty("绩效详情状态")
      private Integer jixiaoremakeState;

      @ApiModelProperty("绩效详情时间")
      private String jixiaoremakeDate;

  @ApiModelProperty("绩效详情总绩效")
  private Double jixiaoremakeJixiao;


}
