package com.zmxrlid.backend.service.impl;

import com.zmxrlid.backend.entity.Taxes;
import com.zmxrlid.backend.mapper.TaxesMapper;
import com.zmxrlid.backend.service.ITaxesService;
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
 * @since 2025-07-30
 */
@Service
public class TaxesServiceImpl extends ServiceImpl<TaxesMapper, Taxes> implements ITaxesService {


    @Resource
    private TaxesMapper taxesMapper;

    @Override
    public List<Taxes> getClassnum(Integer teacherId, String date) {
        return taxesMapper.getClassnum(teacherId, date);
    }
}
