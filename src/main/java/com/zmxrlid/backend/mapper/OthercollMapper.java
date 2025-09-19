package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Othercoll;
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
 * @since 2025-09-06
 */
@Mapper
public interface OthercollMapper extends BaseMapper<Othercoll> {

    @Select("select * from othercoll where othercoll_masterid=#{masterId} and othercoll_date=#{date}")
    Othercoll beginfandmasterid(@Param("masterId") Integer masterId,@Param("date") String date);

    @Select("select * from othercoll where othercoll_masterid=#{masterId} and othercoll_date=#{date}")
    Othercoll fandmasterid(@Param("masterId") Integer masterId,@Param("date") String date);
}
