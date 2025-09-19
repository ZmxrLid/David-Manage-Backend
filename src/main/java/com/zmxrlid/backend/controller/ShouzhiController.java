package com.zmxrlid.backend.controller;


import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.entity.Shouzhi;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.IOthercollService;
import com.zmxrlid.backend.service.ISalaryService;
import com.zmxrlid.backend.service.IShouzhiService;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@RestController
@RequestMapping("/shouzhi")
public class ShouzhiController {

    @Resource
    private IShouzhiService shouzhiService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IOthercollService othercollService;

    @RequestMapping("/createmaster")
    public Result createMaster(@RequestParam Integer masterId,
                               @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        return Result.error("已存在");
    }

    @RequestMapping("/flushedmaster")
    public Result flushed(@RequestParam Integer masterId,
                          @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        Shouzhi shouzhi = shouzhiService.getforhave(masterId,date);
        if (shouzhi.getShouzhiState() == 1){
            return Result.error("不可修改");
        }
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
        Double jixiao = 0.00;
        Double gongzi = 0.00;
        for(Teacher teacher:teacherList){
            Salary salary = salaryService.getnumbyteacherid(teacher.getTeacherId(), date);
            if (salary != null){
                if (salary.getSalaryJixiao() != null){
                    jixiao += salary.getSalaryJixiao();
                }
                if (salary.getSalaryNum() != null){
                    gongzi += salary.getSalaryNum();
                }
            }
        }
        gongzi += othercollService.fandmasterid(masterId,date).getOthercollOthercoll();
        gongzi += othercollService.fandmasterid(masterId,date).getOthercollMastercoll();
        shouzhi.setShouzhiJixiao(jixiao);
        shouzhi.setShouzhiGongzi(gongzi);
        shouzhi.setShouzhiNum(shouzhi.getShouzhiJixiao()-shouzhi.getShouzhiGongzi()-shouzhi.getShouzhiFangzu()-shouzhi.getShouzhiShui()-shouzhi.getShouzhiZhi()+shouzhi.getShouzhiShou()-shouzhi.getShouzhiDian()-shouzhi.getShouzhiWuye());
        shouzhiService.updateById(shouzhi);
        return Result.suc();
    }

    @RequestMapping("/get")
    public Result get(@RequestParam Integer masterId,
                      @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        Shouzhi shouzhi = shouzhiService.getforhave(masterId,date);
        if (shouzhi == null){
            return Result.error("未找到");
        }
        return Result.suc(shouzhi);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Shouzhi shouzhi){
        if (shouzhi.getShouzhiState() == 1){
            return Result.error("不可修改");
        }
        return shouzhiService.saveOrUpdate(shouzhi)?Result.suc():Result.error();
    }

    @Transactional
    @RequestMapping("/ctrls")
    public Result ctrls(@RequestBody Shouzhi shouzhi){
        shouzhi.setShouzhiState(1);
        List<Teacher> teacherList = teacherService.fandnumbymasterid(shouzhi.getShouzhiMasterid());
        for (Teacher teacher : teacherList){
            Salary salary = salaryService.fandnumbyteacherid(teacher.getTeacherId(),shouzhi.getShouzhiDate());
            if (salary != null){
                salaryService.shuaxin(salary);
            }
        }
        return shouzhiService.saveOrUpdate(shouzhi)?Result.suc():Result.error();
    }

}

