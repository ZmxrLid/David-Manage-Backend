package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Salary;
import com.zmxrlid.backend.entity.Shouzhi;
import com.zmxrlid.backend.entity.Teacher;
import com.zmxrlid.backend.mapper.ShouzhiMapper;
import com.zmxrlid.backend.service.ISalaryService;
import com.zmxrlid.backend.service.IShouzhiService;
import com.zmxrlid.backend.service.IOthercollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxrlid.backend.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
@Service
public class ShouzhiServiceImpl extends ServiceImpl<ShouzhiMapper, Shouzhi> implements IShouzhiService {


    @Resource
    private ShouzhiMapper shouzhiMapper;

    @Resource
    private ITeacherService teacherService;

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IOthercollService othercollService;

    @Override
    public Shouzhi getforhave(Integer masterId, String date) {
        return shouzhiMapper.getforhave(masterId,date);
    }

    @Override
    public Boolean chuangjian(Integer masterId, String date) {
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
        this.save(shouzhi);
        return null;
    }
}
