package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
public interface IClassService extends IService<Class> {

    List<Class> classByMaster(Integer masterId);

    List<Class> classByTeacher(Integer teacherId);

    Class getByTeacherId(Integer teacherId);
}
