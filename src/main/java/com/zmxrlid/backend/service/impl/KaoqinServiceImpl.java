package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Kaoqin;
import com.zmxrlid.backend.mapper.KaoqinMapper;
import com.zmxrlid.backend.service.IKaoqinService;
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
 * @since 2025-08-13
 */
@Service
public class KaoqinServiceImpl extends ServiceImpl<KaoqinMapper, Kaoqin> implements IKaoqinService {


    @Resource
    private KaoqinMapper kaoqinMapper;

    @Override
    public Kaoqin getforhave(Integer teacherId, String date) {
        return kaoqinMapper.getforhave(teacherId,date);
    }

    @Override
    public Double getnumbyteacherid(Integer teacherId, String date) {
        return kaoqinMapper.getnumbyteacherid(teacherId,date);
    }

    @Override
    public List<Kaoqin> fandall(Integer masterId, String date) {
        return kaoqinMapper.fandall(masterId,date);
    }

}
