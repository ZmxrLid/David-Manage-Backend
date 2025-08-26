package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Student;
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
 * @since 2025-07-24
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from student where student_classid = #{studentClassid}")
    List<Student> gaiClassteacher(@Param("studentClassid") Integer studentClassid);
}
