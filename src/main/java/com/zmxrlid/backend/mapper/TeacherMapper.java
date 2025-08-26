package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select("select * from teacher where teacher_masterid = #{masterId}")
    List<Teacher> teaForClass(Integer masterId);

    @Select("select * from teacher where teacher_masterid = #{masterId}")
    List<Teacher> fandnumbymasterid (Integer masterId);

    @Select("select teacher_kaoqin from teacher where teacher_id = #{teacherId}")
    Double getnumbyid(Integer teacherId);
}
