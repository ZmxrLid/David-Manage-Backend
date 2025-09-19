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
  @ApiModel(value = "Othercoll对象", description = "")
public class Othercoll implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("其他工资id")
        @TableId(value = "othercoll_id", type = IdType.AUTO)
      private Integer othercollId;

      @ApiModelProperty("其他工资校长id")
      private Integer othercollMasterid;

      @ApiModelProperty("其他工资时间")
        private String othercollDate;

      @ApiModelProperty("其他工资校长工资")
      private Double othercollMastercoll;

      @ApiModelProperty("其他工资其他工资")
      private Double othercollOthercoll;

      @ApiModelProperty("其他工资备注")
      private String othercollRemake;

      @ApiModelProperty("其他工资状态")
      private Integer othercollState;


}
