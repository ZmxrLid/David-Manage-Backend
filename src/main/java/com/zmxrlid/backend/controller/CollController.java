package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Coll;
import com.zmxrlid.backend.entity.Student;
import com.zmxrlid.backend.service.ICollService;
import com.zmxrlid.backend.service.IStudentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.math.BigDecimal;
import java.math.RoundingMode;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/coll")
public class CollController {

    @Resource
    private ICollService collService;

    @Resource
    private IStudentService studentService;

    @Transactional
    @RequestMapping("/add")
    public Result add(@RequestBody Coll coll) {
        Integer stuId = coll.getCollStuid();
        Student student = studentService.getById(stuId);
        student.setStudentHours(student.getStudentHours() + coll.getCollHours());
        student.setStudentZhours(student.getStudentZhours() + coll.getCollHours());
        student.setStudentClasscoll(coll.getCollNum()/coll.getCollHours());
        if (studentService.updateById(student)){
            coll.setCollState(1);
            return collService.save(coll) ? Result.suc() : Result.error();
        }else {
            return Result.error();
        }
    }

    @Transactional
    @RequestMapping("/addteacher")
    public Result addteacher(@RequestBody Coll coll) {
        coll.setCollState(0);
        return collService.save(coll) ? Result.suc() : Result.error();
    }

    @RequestMapping("/pageMaster")
    public Page<Coll> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam Integer masterId,
                               @RequestParam(defaultValue = "") String studentName){
        Page<Coll> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Coll> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("coll_stuname",studentName).eq("coll_masterid",masterId).orderByDesc("coll_time");
        return collService.page(page,queryWrapper);
    }

    @Transactional
    @RequestMapping("/approve")
    public Boolean approve(@RequestParam Integer collId){
        Coll coll = collService.getById(collId);
        coll.setCollState(1);
        Student student = studentService.getById(coll.getCollStuid());
        student.setStudentHours(student.getStudentHours() + coll.getCollHours());
        student.setStudentZhours(student.getStudentZhours() + coll.getCollHours());
        student.setStudentClasscoll(coll.getCollNum()/coll.getCollHours());
        if (collService.updateById(coll) && studentService.updateById(student)){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/reject")
    public Boolean reject(@RequestParam Integer collId) {
        Coll coll = collService.getById(collId);
        coll.setCollState(2);
        return collService.updateById(coll);
    }

    @RequestMapping("/cancelReject")
    public Boolean cancelReject(@RequestParam Integer collId) {
        Coll coll = collService.getById(collId);
        coll.setCollState(0);
        return collService.updateById(coll);
    }

    @RequestMapping("/cancelrejectteacher")
    public Boolean cancelrejectteacher(@RequestParam Integer collId) {
        Coll coll = collService.getById(collId);
        coll.setCollState(0);
        return collService.updateById(coll);
    }

    @RequestMapping("/collpageteacher")
    public Page<Coll> collpageTeacher(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam Integer teacherId){
        Page<Coll> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Coll> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coll_collid",teacherId).orderByDesc("coll_time");
        return collService.page(page,queryWrapper);
    }

    @RequestMapping("/zongmonth")
    public Double zongmonth(@RequestParam Integer masterId, @RequestParam String date){
        double zongmonth = 0.0;
        for (Coll coll : collService.list(new QueryWrapper<Coll>().like("coll_time",date).eq("coll_masterid",masterId).eq("coll_state",1))){
            if (coll.getCollState() == 1&&coll.getCollShijinum()!=null){
                zongmonth += coll.getCollShijinum();
            }
        }
        return zongmonth;
    }
}

