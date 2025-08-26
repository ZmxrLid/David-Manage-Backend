package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
public interface ISalaryService extends IService<Salary> {

    Salary getnumbyteacherid(Integer teacherId, String date);

    Salary fandnumbyteacherid(Integer teacherId, String date);

    Salary getforteacher(Integer teacherId, String date);
}
