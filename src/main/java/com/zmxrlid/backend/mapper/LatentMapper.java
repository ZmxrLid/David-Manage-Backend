package com.zmxrlid.backend.mapper;

import com.zmxrlid.backend.entity.Latent;
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
 * @since 2025-08-02
 */
@Mapper
public interface LatentMapper extends BaseMapper<Latent> {

    @Select("select * from latent where latent_fromname = #{teacherName} and latent_ctime LIKE #{date} and latent_state = 3")
    List<Latent> fromname(@Param("teacherName") String teacherName,@Param("date") String date);

    @Select("select * from latent where latent_telname = #{teacherName} and latent_ctime LIKE #{date} and latent_state = 3")
    List<Latent> telname(@Param("teacherName") String teacherName,@Param("date") String date);

    @Select("select * from latent where latent_teachername = #{teacherName} and latent_ctime LIKE #{date} and latent_state = 3")
    List<Latent> teachername(@Param("teacherName") String teacherName,@Param("date") String date);

    @Select("select * from latent where latent_talkname = #{teacherName} and latent_ctime LIKE #{date} and latent_state = 3")
    List<Latent> talkname(@Param("teacherName") String teacherName,@Param("date") String date);

    @Select("select * from latent where latent_speech = #{teacherName} and latent_ctime LIKE #{date} and latent_state = 3")
    List<Latent> speech(@Param("teacherName") String teacherName,@Param("date") String date);
}
