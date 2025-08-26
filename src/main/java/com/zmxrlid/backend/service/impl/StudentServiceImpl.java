package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Student;
import com.zmxrlid.backend.mapper.StudentMapper;
import com.zmxrlid.backend.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {


    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<Student> gaiClassteacher(Integer classId) {
        return studentMapper.gaiClassteacher(classId);
    }
}
