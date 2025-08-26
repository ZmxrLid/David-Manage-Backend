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
  @ApiModel(value = "Class对象", description = "")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("学科id")
        @TableId(value = "class_id", type = IdType.AUTO)
      private Integer classId;

      @ApiModelProperty("学科名字")
      private String className;
  @ApiModelProperty("学科所属老师id")
  private Integer classTeacherid;
  @ApiModelProperty("学科所属校长id")
  private Integer classMasterid;


}
