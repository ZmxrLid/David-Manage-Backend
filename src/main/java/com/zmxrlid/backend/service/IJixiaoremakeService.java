package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Jixiaoremake;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-09-06
 */
public interface IJixiaoremakeService extends IService<Jixiaoremake> {

    List<Jixiaoremake> fandallmasterid(Integer masterId, String date);
}
