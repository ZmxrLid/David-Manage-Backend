package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.mapper.SalaryMapper;
import com.zmxrlid.backend.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {


    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public Salary getnumbyteacherid(Integer teacherId, String date) {
        return salaryMapper.getnumbyteacherid(teacherId,date);
    }

    @Override
    public Salary fandnumbyteacherid(Integer teacherId, String date) {
        return salaryMapper.fandnumbyteacherid(teacherId,date);
    }

    @Override
    public Salary getforteacher(Integer teacherId, String date) {
        return salaryMapper.getforteacher(teacherId,date);
    }
}
