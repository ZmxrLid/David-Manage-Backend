package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Class;
import com.zmxrlid.backend.mapper.ClassMapper;
import com.zmxrlid.backend.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Resource
    private ClassMapper classMapper;
    @Override
    public List<Class> classByMaster(Integer masterId) {
        return classMapper.classByMaster(masterId);
    }

    @Override
    public List<Class> classByTeacher(Integer teacherId) {
        return classMapper.classByTeacher(teacherId);
    }

    @Override
    public Class getByTeacherId(Integer teacherId) {
        return classMapper.getByTeacherId(teacherId);
    }
}
