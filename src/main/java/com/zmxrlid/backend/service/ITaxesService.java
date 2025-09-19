package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Taxes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-07-30
 */
public interface ITaxesService extends IService<Taxes> {

    List<Taxes> getClassnum(Integer teacherId, String date);
}
