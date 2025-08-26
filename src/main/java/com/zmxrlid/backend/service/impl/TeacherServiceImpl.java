package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.mapper.TeacherMapper;
import com.zmxrlid.backend.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> teaForClass(Integer masterId) {
        return teacherMapper.teaForClass(masterId);
    }

    @Override
    public List<Teacher> fandnumbymasterid(Integer masterId) {
        return teacherMapper.fandnumbymasterid(masterId);
    }

    @Override
    public Double getnumbyid(Integer teacherId) {
        return teacherMapper.getnumbyid(teacherId);
    }
}
