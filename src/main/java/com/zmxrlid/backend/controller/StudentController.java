package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Class;
import com.zmxrlid.backend.entity.Student;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.IClassService;
import com.zmxrlid.backend.service.IStudentService;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @Resource
    private IClassService classService;

    @Resource
    private ITeacherService teacherService;

    @RequestMapping("/add")
    public Result add(@RequestBody Student student) {
        if (student.getStudentClassid()== null){
            return Result.error();
        }
        Class clazz = classService.getById(student.getStudentClassid());
        student.setStudentState(1);
        student.setStudentClassname(clazz.getClassName());
        student.setStudentTeachername(teacherService.getById(clazz.getClassTeacherid()).getTeacherName());
        student.setStudentHours(0);
        student.setStudentZhours(0);
        student.setStudentTeacherid(clazz.getClassTeacherid());
        student.setStudentClasscoll(0.00);
        return studentService.save(student)?Result.suc():Result.error();
    }

    @GetMapping("/page")
    public Page<Student> findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer masterId,
                                  @RequestParam(defaultValue = "") String studentName){
        Page<Student> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name",studentName).eq("student_masterid",masterId);
        return studentService.page(page,queryWrapper);
    }

    @GetMapping("/pageteacher")
    public Page<Student> pageteacher(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer teacherId,
                                  @RequestParam(defaultValue = "") String studentName){
        Page<Student> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name",studentName).eq("student_teacherid",teacherId);
        return studentService.page(page,queryWrapper);
    }

    @PostMapping("/update")
    public Result save(@RequestBody Student student) {
        student.setStudentClassname(classService.getById(student.getStudentClassid()).getClassName());
        Integer teacherId = classService.getById(student.getStudentClassid()).getClassTeacherid();
        student.setStudentTeacherid(teacherId);
        student.setStudentTeachername(teacherService.getById(teacherId).getTeacherName());
        return studentService.saveOrUpdate(student)?Result.suc():Result.error();
    }

    @PostMapping("/delect")
    public boolean delete(@RequestParam Integer studentId) {
        Student student = studentService.getById(studentId);
        student.setStudentState(0);
        return studentService.updateById(student);
    }


    @RequestMapping("/getClassStudentPage")
    public Page<Student> getClassStudentPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam Integer classId,
                                      @RequestParam(defaultValue = "") String studentName){
        Page<Student> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("student_name",studentName).eq("student_classid",classId).eq("student_state",1);
        return studentService.page(page,queryWrapper);
    }

    @RequestMapping("/getStudentFromDelet")
    public List<Student> getStudentFromDelet(@RequestParam Integer classId){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_classid",classId).eq("student_state",1);
        return studentService.list(queryWrapper);
    }

    @RequestMapping("/stuclasscollbyid")
    public Double stuclasscollbyid(@RequestParam Integer studentId){
        Student student = studentService.getById(studentId);
        return student.getStudentClasscoll();
    }
}

