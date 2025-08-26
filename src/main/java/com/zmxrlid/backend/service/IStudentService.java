package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Student;
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
public interface IStudentService extends IService<Student> {

    List<Student> gaiClassteacher(Integer classId);
}
