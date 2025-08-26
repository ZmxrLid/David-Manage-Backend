package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Master;
import com.zmxrlid.backend.service.IMasterService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/master")
public class MasterController {


    @Resource
    private IMasterService masterService;

    @PostMapping("/login")
    public Result login(@RequestBody Master master) {
        String mastername = master.getMasterName();
        String masterpassword = master.getMasterPassword();
        if (StringUtils.isBlank(masterpassword)||StringUtils.isBlank(mastername)){
            return Result.error();
        }
        QueryWrapper<Master> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("master_name",master.getMasterName());
        queryWrapper.eq("master_password",master.getMasterPassword());
        Master dto = masterService.getOne(queryWrapper);
        if (dto!=null){
            return Result.suc(dto);
        }
        return Result.error();
    }

    @GetMapping("/get")
    public Result get(@RequestParam Integer masterId) {
        return Result.suc(masterService.getById(masterId));
    }

    @PostMapping("/update")
    public Result update(@RequestBody Master master) {
        return Result.suc(masterService.updateById(master));
    }

}

