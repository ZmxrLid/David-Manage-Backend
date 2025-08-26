package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-24
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        String teachername = teacher.getTeacherName();
        String teacherpassword = teacher.getTeacherPassword();
        if (StringUtils.isBlank(teacherpassword)||StringUtils.isBlank(teachername)){
            return Result.error();
        }
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_name",teacher.getTeacherName());
        queryWrapper.eq("teacher_password",teacher.getTeacherPassword());
        Teacher dto = teacherService.getOne(queryWrapper);
        if (dto!=null){
            return Result.suc(dto);
        }
        return Result.error();
    }

    @GetMapping("/teacherByMaster")
    public Page<Teacher> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam Integer masterId,
                               @RequestParam(defaultValue = "") String teacherName){
        Page<Teacher> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("teacher_name",teacherName).eq("teacher_state",1).eq("teacher_masterid",masterId);
        return teacherService.page(page,queryWrapper);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher) {
        boolean save = teacherService.saveOrUpdate(teacher);
        if (save){
            return Result.suc();
        }
        return Result.error();
    }

    @PostMapping("/update")
    public Result save(@RequestBody Teacher teacher) {
        boolean save = teacherService.saveOrUpdate(teacher);
        if (save){
            return Result.suc();
        }
        return Result.error();
    }

    @PostMapping("/delect")
    public boolean delete(@RequestParam Integer teacherId){
        UpdateWrapper<Teacher> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("teacher_id", teacherId).set("teacher_state", 0);
        return teacherService.update(updateWrapper);
    }

    @GetMapping("/teaForClass")
    public Result teaForClass(@RequestParam Integer masterId){
        return Result.suc(teacherService.teaForClass(masterId));
    }

    @GetMapping("/getByteacherId")
    public Result getByteacherId(@RequestParam Integer teacherId){
        return Result.suc(teacherService.getById(teacherId));
    }

}

