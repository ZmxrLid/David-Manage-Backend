package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Salary;
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
public interface SalaryMapper extends BaseMapper<Salary> {

    @Select("select * from salary where salary_teacherid=#{teacherId} and salary_date=#{date}")
    Salary getnumbyteacherid(@Param("teacherId") Integer teacherId,@Param("date") String date);

    @Select("select * from salary where salary_teacherid= #{teacherId} and salary_date= #{date}")
    Salary fandnumbyteacherid(@Param("teacherId") Integer teacherId,@Param("date") String date);

    @Select("select * from salary where salary_teacherid= #{teacherId} and salary_date= #{date}")
    Salary getforteacher(@Param("teacherId") Integer teacherId,@Param("date") String date);
}
