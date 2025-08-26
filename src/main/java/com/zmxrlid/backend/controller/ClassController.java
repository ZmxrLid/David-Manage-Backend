package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Class;
import com.zmxrlid.backend.entity.Student;
import com.zmxrlid.backend.service.IClassService;
import com.zmxrlid.backend.service.IStudentService;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private IClassService classService;

    @Resource
    private IStudentService studentService;

    @Resource
    private ITeacherService teacherService;

    @RequestMapping("/classByMaster")
    public Result classByMaster(@RequestParam Integer masterId){
        return Result.suc(classService.classByMaster(masterId));
    }

    @RequestMapping("/classByTeacher")
    public Result classByTeacher(@RequestParam Integer teacherId){
        return Result.suc(classService.classByTeacher(teacherId));
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Class classes){
        if (classService.saveOrUpdate(classes)){
            return Result.suc();
        }
        return Result.error();
    }

    @RequestMapping("/getClassName")
    public Result getClassName(@RequestParam Integer classId){
        return Result.suc(classService.getById(classId));
    }

    @RequestMapping("/updateClass")
    public Result updateClass(@RequestBody Class classes){
        if (classService.updateById(classes)){
            return Result.suc();
        }
        return Result.error();
    }

    @Transactional
    @RequestMapping("/updateClassTeacher")
    public Result updateClassTeacher(@RequestBody Class classes){
        Integer classId = classes.getClassId();
        Integer teacherId = classes.getClassTeacherid();
        String className = classes.getClassName();
        String teacherName = teacherService.getById(teacherId).getTeacherName();
        List<Student> students = studentService.gaiClassteacher(classId);
        int i = 0;
        for (Student student : students) {
            student.setStudentClassname(className);
            student.setStudentTeachername(teacherName);
            student.setStudentTeacherid(teacherId);
            student.setStudentClassid(classId);
            if (studentService.updateById(student)){
                i++;
            }
        }
        if (classService.updateById(classes)&&i==students.size()){
            return Result.suc();
        }
        return Result.error();
    }

    @GetMapping("/getbyteacherid")
    public Result get(@RequestParam Integer classId){
        return Result.suc(classService.getById(classId));
    }

}

