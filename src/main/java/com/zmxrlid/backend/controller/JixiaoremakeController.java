package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.common.StringToList;
import com.zmxrlid.backend.entity.*;
import com.zmxrlid.backend.service.*;
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
 * @since 2025-09-06
 */
@RestController
@RequestMapping("/jixiaoremake")
public class JixiaoremakeController {

    @Resource
    private IJixiaoremakeService jixiaoremakeService;

    @Resource
    private IKaoqinService kaoqinService;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private ITaxesService taxesService;

    @Resource
    private IStudentService studentService;

    @RequestMapping("/begin")
    public Result begin(@RequestParam Integer masterId,@RequestParam String date){
        List<Kaoqin> kaoqinList = kaoqinService.fandall(masterId,date);
        if (kaoqinList==null){
            return Result.error("未创建考勤表");
        }
        for (Kaoqin kaoqin:kaoqinList){
            if (kaoqin.getKaoqinState()==0){
                return Result.error("考勤未全部确认");
            }
        }

        if (jixiaoremakeService.fandallmasterid(masterId, date).isEmpty()){
            List<Teacher> teacherList = teacherService.fandnumbymasterid(masterId);
            String dateLike = date+"%";
            for(Teacher teacher:teacherList){
                Jixiaoremake jixiaoremake = new Jixiaoremake();
                jixiaoremake.setJixiaoremakeMasterid(masterId);
                jixiaoremake.setJixiaoremakeTeacherid(teacher.getTeacherId());
                jixiaoremake.setJixiaoremakeTeachername(teacher.getTeacherName());
                jixiaoremake.setJixiaoremakeDate(date);
                jixiaoremake.setJixiaoremakeState(0);
                jixiaoremake.setJixiaoremakeKouchu(0.00);

                List<Taxes> taxesList = taxesService.getClassnum(teacher.getTeacherId(), dateLike);
                if (taxesList==null){
                    jixiaoremake.setJixiaoremakeClassnum(0);
                    jixiaoremake.setJixiaoremakeStudentnum(0);
                    jixiaoremake.setJixiaoremakeJixiao(0.00);
                }else {
                    jixiaoremake.setJixiaoremakeClassnum(taxesList.size());

                    Integer studentnum = 0;
                    Double jixiao = 0.00;
                    for (Taxes taxes:taxesList) {
                        List<Integer> studentIdList;
                        studentIdList = StringToList.parseStringToList(taxes.getTaxesStuidlist());
                        for (Integer studentId : studentIdList){
                            Student student = studentService.getById(studentId);
                            studentnum += 1;
                            jixiao += student.getStudentClasscoll();
                        }
                    }

                    jixiaoremake.setJixiaoremakeStudentnum(studentnum);
                    jixiaoremake.setJixiaoremakeJixiao(jixiao);
                }
                jixiaoremakeService.saveOrUpdate(jixiaoremake);
            }
        }else {
            return Result.suc();
        }
        return Result.error();
    }

    @RequestMapping("/uploadmaster")
    public Result uploadmaster(@RequestBody Jixiaoremake jixiaoremake){
        jixiaoremake.setJixiaoremakeJixiao(jixiaoremake.getJixiaoremakeJixiao()-jixiaoremake.getJixiaoremakeKouchu());
        return jixiaoremakeService.saveOrUpdate(jixiaoremake) ? Result.suc() : Result.error();
    }

    @RequestMapping("/flushedmaster")
    public Result flushed(@RequestParam Integer masterId, @RequestParam String date){
        if (masterId == null || date == null){
            return Result.error("参数错误");
        }
        List<Jixiaoremake> jixiaoremakeList = jixiaoremakeService.fandallmasterid(masterId,date);
        if (jixiaoremakeList == null){
            return Result.error("未创建");
        }

        String dateLike = date+"%";
        for (Jixiaoremake jixiaoremake : jixiaoremakeList){
            if (jixiaoremake.getJixiaoremakeState()==0){
                List<Taxes> taxesList = taxesService.getClassnum(jixiaoremake.getJixiaoremakeTeacherid(), dateLike);
                if (taxesList==null){
                    jixiaoremake.setJixiaoremakeClassnum(0);
                    jixiaoremake.setJixiaoremakeStudentnum(0);
                    jixiaoremake.setJixiaoremakeJixiao(0.00);
                }else {
                    jixiaoremake.setJixiaoremakeClassnum(taxesList.size());

                    Integer studentnum = 0;
                    Double jixiao = 0.00;
                    for (Taxes taxes:taxesList) {
                        List<Integer> studentIdList;
                        studentIdList = StringToList.parseStringToList(taxes.getTaxesStuidlist());
                        for (Integer studentId : studentIdList){
                            Student student = studentService.getById(studentId);
                            studentnum += 1;
                            jixiao += student.getStudentClasscoll();
                        }
                    }

                    jixiaoremake.setJixiaoremakeStudentnum(studentnum);
                    jixiaoremake.setJixiaoremakeJixiao(jixiao-jixiaoremake.getJixiaoremakeKouchu());
                }
                jixiaoremakeService.saveOrUpdate(jixiaoremake);
            }
        }
        return Result.suc();
    }

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestBody Jixiaoremake jixiaoremake){
        jixiaoremake.setJixiaoremakeState(1);
        if(jixiaoremakeService.updateById(jixiaoremake)){
            return Result.suc();
        }else {
            return Result.error("保存失败");
        }
    }

    @RequestMapping("/pagemaster")
    public Page<Jixiaoremake> page(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String date,
                             @RequestParam Integer masterId){
        Page<Jixiaoremake> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Jixiaoremake> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("jixiaoremake_masterid",masterId).eq("jixiaoremake_date",date);
        return jixiaoremakeService.page(page,queryWrapper);
    }

}

