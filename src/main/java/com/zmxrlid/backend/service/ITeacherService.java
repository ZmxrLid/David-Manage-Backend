package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
public interface ITeacherService extends IService<Teacher> {

    List<Teacher> teaForClass(Integer masterId);

    List<Teacher> fandnumbymasterid(Integer masterId);

    Double getnumbyid(Integer teacherId);
}
