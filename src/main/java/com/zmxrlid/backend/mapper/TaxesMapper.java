package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Taxes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-30
 */
@Mapper
public interface TaxesMapper extends BaseMapper<Taxes> {

    @Select("SELECT * FROM taxes where taxes_state = 1 and taxes_teacherid = #{teacherId} and taxes_datetime like #{date}")
    List<Taxes> getClassnum(@Param("teacherId") Integer teacherId,@Param("date") String date);
}
