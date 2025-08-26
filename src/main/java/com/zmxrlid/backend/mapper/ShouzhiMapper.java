package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Shouzhi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@Mapper
public interface ShouzhiMapper extends BaseMapper<Shouzhi> {

    @Select("select * from shouzhi where shouzhi_masterid=#{masterId} and shouzhi_date=#{date}")
    Shouzhi getforhave(@Param("masterId") Integer masterId,@Param("date") String date);
}
