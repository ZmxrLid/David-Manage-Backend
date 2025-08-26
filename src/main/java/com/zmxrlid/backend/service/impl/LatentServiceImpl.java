package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Latent;
import com.zmxrlid.backend.mapper.LatentMapper;
import com.zmxrlid.backend.service.ILatentService;
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
 * @since 2025-08-02
 */
@Service
public class LatentServiceImpl extends ServiceImpl<LatentMapper, Latent> implements ILatentService {

    @Resource
    private LatentMapper latentMapper;


    @Override
    public List<Latent> fromname(String teacherName, String date) {
        return latentMapper.fromname(teacherName, date);
    }

    @Override
    public List<Latent> telname(String teacherName, String date) {
        return latentMapper.telname(teacherName, date);
    }

    @Override
    public List<Latent> teachername(String teacherName, String date) {
        return latentMapper.teachername(teacherName, date);
    }

    @Override
    public List<Latent> talkname(String teacherName, String date) {
        return latentMapper.talkname(teacherName, date);
    }

    @Override
    public List<Latent> speech(String teacherName, String date) {
        return latentMapper.speech(teacherName, date);
    }
}
