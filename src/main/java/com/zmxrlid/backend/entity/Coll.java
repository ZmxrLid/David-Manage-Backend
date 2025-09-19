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
  @ApiModel(value = "Coll对象", description = "")
public class Coll implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("账单id")
        @TableId(value = "coll_id", type = IdType.AUTO)
      private Integer collId;

      @ApiModelProperty("账单时间")
      private LocalDateTime collTime;

      @ApiModelProperty("账单所属学生姓名")
      private String collStuname;

      @ApiModelProperty("账单所属学生id")
      private Integer collStuid;

      @ApiModelProperty("账单操作人员名字")
      private String collCollname;

      @ApiModelProperty("账单操作人员id")
      private Integer collCollid;

      @ApiModelProperty("账单金额")
      private Double collNum;

      @ApiModelProperty("账单所属学科id")
      private Integer collClassid;

      @ApiModelProperty("账单所属学科名字")
      private String collClassname;

      @ApiModelProperty("账单付款人")
      private String collFromname;

  @ApiModelProperty("增加课时")
  private Integer collHours;

  @ApiModelProperty("校长id")
  private Integer collMasterid;

  @ApiModelProperty("账单状态")
  private Integer collState;

  @ApiModelProperty("账单实际金额")
  private Double collShijinum;


}
