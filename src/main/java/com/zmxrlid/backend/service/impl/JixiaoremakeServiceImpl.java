package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Jixiaoremake;
import com.zmxrlid.backend.mapper.JixiaoremakeMapper;
import com.zmxrlid.backend.service.IJixiaoremakeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 惜喃
 * @since 2025-09-06
 */
@Service
public class JixiaoremakeServiceImpl extends ServiceImpl<JixiaoremakeMapper, Jixiaoremake> implements IJixiaoremakeService {

    @Resource
    private JixiaoremakeMapper jixiaoremakeMapper;

    @Override
    public List<Jixiaoremake> fandallmasterid(Integer masterId, String date) {
        return jixiaoremakeMapper.fandallmasterid(masterId,date);
    }
}
