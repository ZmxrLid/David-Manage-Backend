package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.*;
import com.zmxrlid.backend.service.*;
import com.zmxrlid.backend.service.impl.ShouzhiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Resource
    private ISalaryService salaryService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private IKaoqinService kaoqinService;

    @Resource
    private IShouzhiService shouzhiService;

    @Resource
    private ILatentService latentService;

    @Resource
    private IOthercollService othercollService;

    @Resource
    private IJixiaoremakeService jixiaoremakeService;

    @Transactional
    @RequestMapping("/flushedmaster")
    public Result flushed(@RequestParam Integer masterId,
                          @RequestParam String date){
        //检测
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        if (othercollService.fandmasterid(masterId,date)==null){
            return Result.error("其他工资表未创建");
        }
        Othercoll othercoll = othercollService.fandmasterid(masterId,date);
        if (othercoll.getOthercollState()==0){
            return Result.error("其他工资表未确认");
        }
        List<Kaoqin> kaoqinList = kaoqinService.fandall(masterId,date);
        for (Kaoqin kaoqin : kaoqinList){
            if (kaoqin.getKaoqinState()==0){
                return Result.error("考勤未全部确认");
            }
        }
        List<Jixiaoremake> jixiaoremakeList = jixiaoremakeService.fandallmasterid(masterId,date);
        if (jixiaoremakeList.isEmpty()){
            return Result.error("绩效表未创建");
        }
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
        for (Teacher teacher : teacherList){
            Salary salary = salaryService.fandnumbyteacherid(teacher.getTeacherId(),date);
            if (salary == null&&teacher.getTeacherState()==1){
                salaryService.chuangjian(teacher.getTeacherId(),date);
            }
        }
        if (shouzhiService.getforhave(masterId,date) == null){
            shouzhiService.chuangjian(masterId,date);
        }
        for (Teacher teacher : teacherList){
            Salary salary = salaryService.fandnumbyteacherid(teacher.getTeacherId(),date);
            if (salary != null){
                salaryService.shuaxin(salary);
            }
        }
        return Result.suc("成功");
    }

    @RequestMapping("/pagemaster")
    public Page<Salary> pageMaster(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam Integer masterId,
                             @RequestParam String date){
        QueryWrapper<Salary> queryWrapper = new QueryWrapper<>();
        Page<Salary> page = new Page<>(pageNum,pageSize);
        queryWrapper.eq("salary_masterid",masterId).eq("salary_date",date);
        return salaryService.page(page,queryWrapper);
    }

    @RequestMapping("/updata")
    public Result updata(@RequestBody Salary salary){
        if (salary.getSalaryState()==1){
            return Result.error("已保存不可修改");
        }
        return salaryService.saveOrUpdate(salary)?Result.suc("修改成功"):Result.error();
    }

    @RequestMapping("/getteacher")
    public Result get(@RequestParam Integer teacherId,@RequestParam String date){
        return  salaryService.getforteacher(teacherId,date) == null?Result.error("未找到"):Result.suc(salaryService.getforteacher(teacherId,date));
    }

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestParam Integer masterId,
                       @RequestParam String date){
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
        Shouzhi shouzhi = shouzhiService.getforhave(masterId,date);
        if (shouzhi == null||shouzhi.getShouzhiState()==0){
            return Result.error("请先确认收支表");
        }
        for(Teacher teacher:teacherList){
            if (salaryService.getforteacher(teacher.getTeacherId(),date)!=null){
                Salary salary = salaryService.getforteacher(teacher.getTeacherId(),date);
                salary.setSalaryState(1);
                salaryService.updateById(salary);
            }
        }
        return Result.suc();
    }

}

