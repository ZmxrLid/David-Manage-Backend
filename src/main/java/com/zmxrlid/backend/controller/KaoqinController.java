package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Kaoqin;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.service.IKaoqinService;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-13
 */
@RestController
@RequestMapping("/kaoqin")
public class KaoqinController {

    @Resource
    private IKaoqinService kaoqinService;

    @Resource
    private ITeacherService teacherService;

    @Transactional
    @RequestMapping("/pagemaster")
    public Page<Kaoqin> page(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String date,
                             @RequestParam Integer masterId){
        List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
        for(Teacher teacher:teacherList){
            if (kaoqinService.getforhave(teacher.getTeacherId(), date)==null){
                Kaoqin kaoqin = new Kaoqin();
                kaoqin.setKaoqinMasterid(masterId);
                kaoqin.setKaoqinTeacherid(teacher.getTeacherId());
                kaoqin.setKaoqinDate(date);
                kaoqin.setKaoqinTeachernum(teacher.getTeacherNum());
                kaoqin.setKaoqinKaoqinnum(teacher.getTeacherKaoqin());
                kaoqin.setKaoqinTeachername(teacher.getTeacherName());
                kaoqin.setKaoqinState(0);
                kaoqinService.save(kaoqin);
            }
        }
        Page<Kaoqin> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Kaoqin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kaoqin_masterid",masterId).eq("kaoqin_date",date);
        return kaoqinService.page(page,queryWrapper);
    }

    @Transactional
    @RequestMapping("/uploadmaster")
    public Result uploadmaster(@RequestBody Kaoqin kaoqin){
        if (kaoqin.getKaoqinState()==1){
            return Result.error("已保存");
        }
        Double num = 0.00;
        Double teacherNum = kaoqin.getKaoqinTeachernum();
        Integer ying = kaoqin.getKaoqinYing();
        // 判断空值和除零风险
        if (teacherNum == null || ying == null || ying == 0) {
            return Result.error("应上天数不合规");
        }
        try {
            Double oneDay = teacherNum / ying;
            Double bantian = oneDay / 2;
            num -= kaoqin.getKaoqinTentime() * 10;
            num -= kaoqin.getKaoqinOnehours() * 15;
            num -= kaoqin.getKaoqinTwohours() * 20;
            num -= kaoqin.getKaoqinThreehours() * 25;
            num -= kaoqin.getKaoqinBantian() * bantian;
            num -= kaoqin.getKaoqinQingjia() * oneDay;
            if (kaoqin.getKaoqinDays()>=20){
                if (kaoqin.getKaoqinKaoqinnum()!=0.00) {
                    num += kaoqin.getKaoqinKaoqinnum();
                }else {
                    Double kaoqinNum = teacherService.getnumbyid(kaoqin.getKaoqinTeacherid());
                    num += kaoqinNum;
                    kaoqin.setKaoqinKaoqinnum(kaoqinNum);
                }
            }else{
                kaoqin.setKaoqinKaoqinnum(0.00);
            }
            kaoqin.setKaoqinNum(num);
        } catch (Exception e) {
            return Result.error("参数错误");
        }
        if(kaoqinService.updateById(kaoqin)){
            return Result.suc();
        }else {
            return Result.error("保存失败");
        }
    }

    @RequestMapping("/getteacher")
    public Result getteacher(@RequestParam Integer teacherId,@RequestParam String date){
        Kaoqin kaoqin = kaoqinService.getforhave(teacherId, date);
        return Result.suc(kaoqin);
    }

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestBody Kaoqin kaoqin){
        kaoqin.setKaoqinState(1);
        if(kaoqinService.updateById(kaoqin)){
            return Result.suc();
        }else {
            return Result.error("保存失败");
        }
    }

}

