package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Kaoqin;
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
 * @since 2025-08-13
 */
@Mapper
public interface KaoqinMapper extends BaseMapper<Kaoqin> {

    @Select("select * from kaoqin where kaoqin_teacherid=#{teacherId} and kaoqin_date=#{date}")
    Kaoqin getforhave(@Param("teacherId") Integer teacherId,@Param("date") String date);

    @Select("select kaoqin_num from kaoqin where kaoqin_teacherid= #{teacherId} and kaoqin_date= #{date}")
    Double getnumbyteacherid(@Param("teacherId") Integer teacherId,@Param("date") String date);

}
