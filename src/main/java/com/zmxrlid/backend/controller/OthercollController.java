package com.zmxrlid.backend.controller;


import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Jixiaoremake;
import com.zmxrlid.backend.entity.Othercoll;
import com.zmxrlid.backend.service.IJixiaoremakeService;
import com.zmxrlid.backend.service.IOthercollService;
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
@RequestMapping("/othercoll")
public class OthercollController {

    @Resource
    private IJixiaoremakeService jixiaoremakeService;

    @Resource
    private IOthercollService othercollService;

    @RequestMapping("/begin")
    public Result begin(@RequestParam Integer masterId, @RequestParam String date) {
        List<Jixiaoremake> jixiaoremakeList = jixiaoremakeService.fandallmasterid(masterId,date);
        if (jixiaoremakeList==null){
            return Result.error("未创建绩效表");
        }
        for (Jixiaoremake jixiaoremake:jixiaoremakeList){
            if (jixiaoremake.getJixiaoremakeState()==0){
                return Result.error("绩效未全部确认");
            }
        }

        if (othercollService.beginfandmasterid(masterId,date)==null){
            Othercoll othercoll = new Othercoll();
            othercoll.setOthercollMasterid(masterId);
            othercoll.setOthercollDate(date);
            othercoll.setOthercollState(0);
            othercoll.setOthercollMastercoll(0.00);
            othercoll.setOthercollOthercoll(0.00);
            othercollService.save(othercoll);
        }else {
            return Result.suc();
        }
        return Result.suc();
    }

    @RequestMapping("/selectmaster")
    public Result selectmaster(@RequestParam Integer masterId, @RequestParam String date) {
        Othercoll othercoll = othercollService.fandmasterid(masterId,date);
        return Result.suc(othercoll);
    }

    @RequestMapping("/uploadmaster")
    public Result uploadmaster(@RequestBody Othercoll othercoll) {
        if (othercoll.getOthercollState()==1){
            return Result.error("已保存不可修改");
        }
        return othercollService.saveOrUpdate(othercoll) ? Result.suc() : Result.error();
    }

    @RequestMapping("/ctrls")
    public Result ctrls(@RequestParam Integer masterId, @RequestParam String date) {
        Othercoll othercoll = othercollService.fandmasterid(masterId,date);
        othercoll.setOthercollState(1);
        return othercollService.updateById(othercoll) ? Result.suc() : Result.error();
    }
}

