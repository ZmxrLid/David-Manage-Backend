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
 * @since 2025-07-24
 */
@Getter
@Setter
  @ApiModel(value = "Leads对象", description = "")
public class Leads implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("潜在id")
        @TableId(value = "leads_id", type = IdType.AUTO)
      private Integer leadsId;

      @ApiModelProperty("潜在学校名字 ")
      private String leadsName;

      @ApiModelProperty("潜在学生亲属")
      private String leadsOther;

      @ApiModelProperty("潜在学生亲属电话")
      private String leadsOthertel;

      @ApiModelProperty("潜在学生来源")
      private String leadsFrom;

      @ApiModelProperty("潜在学生联系人")
      private String leadsCom;

      @ApiModelProperty("潜在学生状态")
      private String leadsState;


}
