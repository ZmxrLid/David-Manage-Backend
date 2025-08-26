package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Shouzhi;
import com.zmxrlid.backend.mapper.ShouzhiMapper;
import com.zmxrlid.backend.service.IShouzhiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public Shouzhi getforhave(Integer masterId, String date) {
        return shouzhiMapper.getforhave(masterId,date);
    }
}
