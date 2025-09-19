package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Jixiaoremake;
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
 * @since 2025-09-06
 */
@Mapper
public interface JixiaoremakeMapper extends BaseMapper<Jixiaoremake> {

    @Select("select * from jixiaoremake where jixiaoremake_masterid=#{masterId} and jixiaoremake_date=#{date}")
    List<Jixiaoremake> fandallmasterid(@Param("masterId") Integer masterId,@Param("date") String date);

    @Select("select * from jixiaoremake where jixiaoremake_teacherid= #{teacherId} and jixiaoremake_date= #{date}")
    Jixiaoremake getforteacher(@Param("teacherId") Integer teacherId,@Param("date") String date);
}
