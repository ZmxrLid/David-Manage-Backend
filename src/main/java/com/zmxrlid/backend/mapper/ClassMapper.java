package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface ClassMapper extends BaseMapper<Class> {

    @Select("select * from class where class_masterid = #{masterId}")
    List<Class> classByMaster(Integer masterId);

    @Select("select * from class where class_teacherid = #{teacherId}")
    List<Class> classByTeacher(Integer teacherId);

    @Select("select * from class where class_teacherid = #{teacherId}")
    Class getByTeacherId(Integer teacherId);
}
