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
  @ApiModel(value = "Atten对象", description = "")
public class Atten implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("考勤id")
        @TableId(value = "atten_id", type = IdType.AUTO)
      private Integer attenId;

      @ApiModelProperty("考勤老师id")
      private Integer attenTeacherid;

      @ApiModelProperty("考勤老师姓名")
      private String attenTeachername;

      @ApiModelProperty("考勤时间")
      private LocalDateTime attenTime;

      @ApiModelProperty("考勤")
      private Integer attenHours;

      @ApiModelProperty("考勤校长id")
      private Integer attenMasterid;


}
