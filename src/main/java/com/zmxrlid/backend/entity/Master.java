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
  @ApiModel(value = "Master对象", description = "")
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("校长id")
        @TableId(value = "master_id", type = IdType.AUTO)
      private Integer masterId;

      @ApiModelProperty("校长账号")
      private String masterName;

      @ApiModelProperty("校长密码")
      private String masterPassword;

      @ApiModelProperty("校长等级")
      private Integer masterLv;

  @ApiModelProperty("股份")
  private Integer masterGu;
}
