package com.zmxrlid.backend.controller;


import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.entity.Shouzhi;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.ISalaryService;
import com.zmxrlid.backend.service.IShouzhiService;
import com.zmxrlid.backend.service.ITeacherService;
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

    @RequestMapping("/createmaster")
    public Result createMaster(@RequestParam Integer masterId,
                               @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        if (shouzhiService.getforhave(masterId,date) == null){
            Shouzhi shouzhi = new Shouzhi();
            shouzhi.setShouzhiMasterid(masterId);
            shouzhi.setShouzhiDate(date);
            shouzhi.setShouzhiState(0);
            shouzhi.setShouzhiFangzu(0.00);
            shouzhi.setShouzhiShui(0.00);
            shouzhi.setShouzhiZhi(0.00);
            shouzhi.setShouzhiShou(0.00);
            shouzhi.setShouzhiDian(0.00);
            shouzhi.setShouzhiWuye(0.00);
            shouzhiService.save(shouzhi);
            return Result.suc(shouzhi);
        }
        return Result.error("已存在");
    }

    @RequestMapping("/flushedmaster")
    public Result flushed(@RequestParam Integer masterId,
                          @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        Shouzhi shouzhi = new Shouzhi();
        if (shouzhiService.getforhave(masterId,date) != null){
            shouzhi = shouzhiService.getforhave(masterId,date);
        }else {
            Shouzhi shou = new Shouzhi();
            shou.setShouzhiMasterid(masterId);
            shou.setShouzhiDate(date);
            shou.setShouzhiState(0);
            shouzhi.setShouzhiFangzu(0.00);
            shouzhi.setShouzhiShui(0.00);
            shouzhi.setShouzhiZhi(0.00);
            shouzhi.setShouzhiShou(0.00);
            shouzhi.setShouzhiDian(0.00);
            shouzhi.setShouzhiWuye(0.00);
            shouzhiService.save(shou);
            shouzhi = shouzhiService.getforhave(masterId,date);
        }
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

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestBody Shouzhi shouzhi){
        shouzhi.setShouzhiState(1);
        return shouzhiService.saveOrUpdate(shouzhi)?Result.suc():Result.error();
    }

}

