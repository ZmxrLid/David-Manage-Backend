package com.zmxrlid.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zmxrlid.backend.common.Result;
import com.zmxrlid.backend.entity.Latent;
import com.zmxrlid.backend.common.Quchu;
import com.zmxrlid.backend.service.ILatentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-02
 */
@RestController
@RequestMapping("/latent")
public class LatentController {

    @Resource
    private ILatentService latentService;

    @RequestMapping("/page")
    public Page<Latent> page(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam(defaultValue = "") String stuname,
                             @RequestParam(defaultValue = "") String from,
                             @RequestParam(defaultValue = "") String fromname,
                             @RequestParam(defaultValue = "") String classname,
                             @RequestParam Integer masterid){
        Page<Latent> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Latent> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("latent_from",from).like("latent_fromname",fromname).like("latent_class",classname).like("latent_stuname",stuname).eq("latent_delete",0).eq("latent_masterid",masterid).orderByDesc("latent_id");
        return latentService.page(page,queryWrapper);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Latent latent){
        return latentService.save(latent)?Result.suc():Result.error();
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Latent latent){
        latent.setLatentTime(LocalDateTime.now());
        latent.setLatentEndpeoplename(Quchu.removeQuotesIfPresent(latent.getLatentEndpeoplename()));
        if(latent.getLatentState()==3){
            latent.setLatentCtime(LocalDateTime.now());
        }
        return latentService.updateById(latent)?Result.suc():Result.error();
    }

    @RequestMapping("/delete")
    public Result delete(@RequestParam Integer latentId){
        Latent latent = new Latent();
        latent.setLatentId(latentId);
        latent.setLatentDelete(1);
        return latentService.updateById(latent)?Result.suc():Result.error();
    }

}

