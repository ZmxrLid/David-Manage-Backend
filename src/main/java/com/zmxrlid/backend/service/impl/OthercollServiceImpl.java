package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Othercoll;
import com.zmxrlid.backend.mapper.OthercollMapper;
import com.zmxrlid.backend.service.IOthercollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-09-06
 */
@Service
public class OthercollServiceImpl extends ServiceImpl<OthercollMapper, Othercoll> implements IOthercollService {


    @Resource
    private OthercollMapper othercollMapper;

    @Override
    public Othercoll beginfandmasterid(Integer masterId, String date) {
        return othercollMapper.beginfandmasterid(masterId,date);
    }

    @Override
    public Othercoll fandmasterid(Integer masterId, String date) {
        return othercollMapper.fandmasterid(masterId,date);
    }
}
