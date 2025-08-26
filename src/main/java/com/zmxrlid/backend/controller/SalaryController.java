package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Latent;
import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.entity.Shouzhi;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.*;
import com.zmxrlid.backend.service.impl.ShouzhiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/flushedmaster")
    public Result flushed(@RequestParam Integer masterId,
                          @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
        for (Teacher teacher : teacherList) {
            Salary salary = salaryService.fandnumbyteacherid(teacher.getTeacherId(),date);
            if (salary == null){
                salary = new Salary();
                salary.setSalaryMasterid(masterId);
                salary.setSalaryTeacherid(teacher.getTeacherId());
                salary.setSalaryTeachername(teacher.getTeacherName());
                salary.setSalaryDate(date);
                salary.setSalaryJixiao(0.00);
                salary.setSalaryTicheng(0.00);
                salary.setSalaryExtra(0.00);
                salary.setSalaryState(0);
                salaryService.saveOrUpdate(salary);
            }
            if (teacher.getTeacherState()==1){
                break;
            }else {
                Double yaoyue = 0.00;
                String datemonth = date+"%";
                if (latentService.fromname(teacher.getTeacherName(), datemonth) != null){
                    List<Latent> latentList = latentService.fromname(teacher.getTeacherName(), datemonth);
                    for (Latent latent : latentList) {
                        yaoyue += latent.getLatentNum() * 0.3;
                    }
                }
                if (latentService.telname(teacher.getTeacherName(), datemonth) != null){
                    List<Latent> latentList = latentService.telname(teacher.getTeacherName(), datemonth);
                    for (Latent latent : latentList) {
                        yaoyue += latent.getLatentNum() * 0.1;
                    }
                }
                if (latentService.teachername(teacher.getTeacherName(), datemonth) != null){
                    List<Latent> latentList = latentService.teachername(teacher.getTeacherName(), datemonth);
                    for (Latent latent : latentList) {
                        yaoyue += latent.getLatentNum() * 0.1;
                    }
                }
                if (latentService.talkname(teacher.getTeacherName(), datemonth) != null){
                    List<Latent> latentList = latentService.talkname(teacher.getTeacherName(), datemonth);
                    for (Latent latent : latentList) {
                        yaoyue += latent.getLatentNum() * 0.15;
                    }
                }
                if (latentService.speech(teacher.getTeacherName(), datemonth) != null){
                    List<Latent> latentList = latentService.speech(teacher.getTeacherName(), datemonth);
                    for (Latent latent : latentList) {
                        yaoyue += latent.getLatentNum() * 0.1;
                    }
                }
                salary.setSalaryTicheng(yaoyue);
                if (teacher.getTeacherBaostate()==1){
                    salary.setSalaryBaoxian(teacher.getTeacherBaogong()-teacher.getTeacherBaonum());
                }else {
                    salary.setSalaryBaoxian(0.00);
                }
                salary.setSalaryDixin(teacher.getTeacherNum());
                if (kaoqinService.getnumbyteacherid(teacher.getTeacherId(), date) != null){
                    salary.setSalaryKaoqin(kaoqinService.getnumbyteacherid(teacher.getTeacherId(), date));
                }else {
                    salary.setSalaryKaoqin(0.00);
                }

                // 工龄计算 - 返回整数类型的工龄（单位：年）
                int gongling = 0;
                try {
                    if (teacher.getTeacherTime() != null && !teacher.getTeacherTime().isEmpty()) {
                        // 使用对应格式的 SimpleDateFormat
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date entryDate = sdf.parse(teacher.getTeacherTime());
                        Date currentDate = new Date();

                        // 计算整数年份差
                        long diffInMillis = currentDate.getTime() - entryDate.getTime();
                        // 转换为年数并取整
                        gongling = (int) (diffInMillis / (1000L * 60 * 60 * 24 * 365));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    // 日期解析失败，工龄保持为0
                }
                salary.setSalaryGongling(gongling);
                if (gongling*50>=300){
                    salary.setSalaryGongsalary(300.0);
                }else {
                    salary.setSalaryGongsalary(gongling*50.00);
                }
                switch (teacher.getTeacherJixiao()) {
                    case 1:
                        if (salary.getSalaryJixiao()<=5000){
                            salary.setSalaryJixiaonum(0.00);
                        } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.02);
                        } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                        } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.04);
                        } else if (salary.getSalaryJixiao()>8000){
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                        }
                        // 当expression等于value1时执行
                        break;
                    case 2:
                        if (salary.getSalaryJixiao()<=4000){
                            salary.setSalaryJixiaonum(0.00);
                        } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=6000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.05);
                        } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=8000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.1);
                        } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                        } else if (salary.getSalaryJixiao()>10000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.2);
                        }
                        // 当expression等于value2时执行
                        break;
                    case 3:
                        if (salary.getSalaryJixiao()<=3000){
                            salary.setSalaryJixiaonum(0.00);
                        } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.08);
                        } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.12);
                        } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                        } else if (salary.getSalaryJixiao()>6000&&salary.getSalaryJixiao()<=7000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                        } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                        } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=10000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                        } else if (salary.getSalaryJixiao()>10000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.26);
                        }
                        // 当expression等于value2时执行
                        break;
                    case 4:
                        if (salary.getSalaryJixiao()<=2000){
                            salary.setSalaryJixiaonum(0.00);
                        } else if (salary.getSalaryJixiao()>2000&&salary.getSalaryJixiao()<=3000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.03);
                        } else if (salary.getSalaryJixiao()>3000&&salary.getSalaryJixiao()<=4000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.10);
                        } else if (salary.getSalaryJixiao()>4000&&salary.getSalaryJixiao()<=5000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.13);
                        } else if (salary.getSalaryJixiao()>5000&&salary.getSalaryJixiao()<=6000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.15);
                        } else if (salary.getSalaryJixiao()>7000&&salary.getSalaryJixiao()<=8000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.16);
                        } else if (salary.getSalaryJixiao()>8000&&salary.getSalaryJixiao()<=9000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.19);
                        } else if (salary.getSalaryJixiao()>9000) {
                            salary.setSalaryJixiaonum(salary.getSalaryJixiao()*0.21);
                        }
                        // 当expression等于value2时执行
                        break;
                    default:
                        salary.setSalaryJixiaonum(0.00);
                        // 当expression不匹配任何case时执行
                        break;
                }
                if (teacher.getTeacherGu()==null||teacher.getTeacherGu()==0||shouzhiService.getforhave(masterId,date).getShouzhiNum()==null){
                    salary.setSalaryGufen(0.00);
                }else {
                    salary.setSalaryGufen(shouzhiService.getforhave(masterId,date).getShouzhiNum()*teacher.getTeacherGu()/100);
                }
                salary.setSalaryNum(salary.getSalaryDixin()+salary.getSalaryKaoqin()+salary.getSalaryJixiaonum()+salary.getSalaryTicheng()+salary.getSalaryBaoxian()+salary.getSalaryGongsalary()+salary.getSalaryExtra());
                salary.setSalaryEndnum(salary.getSalaryNum()+salary.getSalaryGufen());
                salaryService.updateById(salary);
            }
        }
        return Result.suc();
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
            return Result.error("请勿重复提交");
        }
        return salaryService.saveOrUpdate(salary)?Result.suc():Result.error();
    }

    @RequestMapping("/getteacher")
    public Result get(@RequestParam Integer teacherId,@RequestParam String date){
        return  salaryService.getforteacher(teacherId,date) == null?Result.error("未找到"):Result.suc(salaryService.getforteacher(teacherId,date));
    }

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestParam Integer masterId,
                       @RequestParam String date){
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
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

