package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.entity.Student;
import com.zmxrlid.backend.entity.Taxes;
import com.zmxrlid.backend.service.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-30
 */
@RestController
@RequestMapping("/taxes")
public class TaxesController {

    @Resource
    private ITaxesService taxesService;

    @Resource
    private IStudentService studentsService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IClassService classService;

    @Resource
    private IStudentService studentService;

    @RequestMapping("/add")
    public Result add(@RequestBody Map<String, Object> requestData) {
        // 从 Map 中提取数据
        List<Integer> studentId = (List<Integer>) requestData.get("studentId");
        Integer classId = Integer.valueOf(requestData.get("classId").toString());
        Integer teacherId = Integer.valueOf(requestData.get("teacherId").toString());
        String remark = (String) requestData.get("remark");
        String datatime = (String) requestData.get("datatime");
        String className = (String) requestData.get("className");

        // 后续处理逻辑保持不变
        StringBuilder studentsName = new StringBuilder();
        for (Integer id : studentId) {
            studentsName.append(studentsService.getById(id).getStudentName()).append(",");
        }
        String studentsId = studentId.toString();
        Taxes taxes = new Taxes();
        taxes.setTaxesRemark(remark);
        taxes.setTaxesDatetime(datatime);
        taxes.setTaxesTeacherid(teacherId);
        taxes.setTaxesTeachername(teacherService.getById(teacherId).getTeacherName());
        taxes.setTaxesClassid(classId);
        taxes.setTaxesStuidlist(studentsId);
        taxes.setTaxesClassname(className);
        taxes.setTaxesStunamelist(studentsName.toString());
        taxes.setTaxesMasterid(classService.getById(classId).getClassMasterid());
        taxes.setTaxesState(0);
        if (taxesService.save(taxes)) {
            return Result.suc();
        }
        return Result.error();
    }

    @RequestMapping("/getDeleteStudent")
    public Page<Taxes> getDeleteStudent(@RequestParam Integer pageNum,
                                             @RequestParam Integer pageSize,
                                             @RequestParam Integer classId){
        Page<Taxes> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Taxes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("taxes_classid",classId).orderByDesc("taxes_time");
        return taxesService.page(page,queryWrapper);
    }

    @RequestMapping("/getDeleteAll")
    public Page<Taxes> getDeleteAll(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize){
        Page<Taxes> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Taxes> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("taxes_time");
        return taxesService.page(page,queryWrapper);
    }

    @RequestMapping("/deletepageteacher")
    public Page<Taxes> pageteacher(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer teacherId){
        Page<Taxes> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Taxes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("taxes_teacherid",teacherId).orderByDesc("taxes_time");
        return taxesService.page(page,queryWrapper);
    }

    @Transactional
    @RequestMapping("/approve")
    public Boolean approve(@RequestParam Integer taxesId) {
        Taxes taxes = taxesService.getById(taxesId);
        String stuIdList = taxes.getTaxesStuidlist().replaceAll("[\\[\\]]", "");
        List<Integer> studentId =  Arrays.stream(stuIdList.split(","))
                .map(String::trim) // 去除空格
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        taxes.setTaxesState(1);
        if (taxesService.updateById(taxes)){
            String datetime = taxes.getTaxesDatetime();
            String[] datelist = datetime.split("-");
            String date = datelist[0]+"-"+datelist[1];
            for (Integer id : studentId){
                Student student = studentService.getById(id);
                Salary salary = salaryService.getforteacher(student.getStudentTeacherid(),date);
                if (salary == null){
                    salary = new Salary();
                    salary.setSalaryMasterid(student.getStudentMasterid());
                    salary.setSalaryTeacherid(student.getStudentTeacherid());
                    salary.setSalaryTeachername(student.getStudentTeachername());
                    salary.setSalaryDate(date);
                    salary.setSalaryTicheng(0.00);
                    salary.setSalaryJixiao(0.00);
                    salary.setSalaryExtra(0.00);
                    salaryService.saveOrUpdate(salary);
                }
                Double classnum = salary.getSalaryJixiao();
                classnum += student.getStudentClasscoll();
                salary.setSalaryJixiao(classnum);
                salaryService.saveOrUpdate(salary);
            }
            for (Integer id : studentId) {
                Student student = studentService.getById(id);
                student.setStudentHours(student.getStudentHours() - 1);
                if (!studentService.updateById(student)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @RequestMapping("/reject")
    public Boolean reject(@RequestParam Integer taxesId) {
        Taxes taxes = taxesService.getById(taxesId);
        taxes.setTaxesState(2);
        return taxesService.updateById(taxes);
    }

    @RequestMapping("/cancelReject")
    public Boolean cancelReject(@RequestParam Integer taxesId) {
        Taxes taxes = taxesService.getById(taxesId);
        taxes.setTaxesState(0);
        return taxesService.updateById(taxes);
    }

    @RequestMapping("/cancelrejectteacher")
    public Boolean cancelrejectteacher(@RequestParam Integer taxesId) {
        Taxes taxes = taxesService.getById(taxesId);
        taxes.setTaxesState(0);
        return taxesService.updateById(taxes);
    }
}

