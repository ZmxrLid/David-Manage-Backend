package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Othercoll;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-09-06
 */
public interface IOthercollService extends IService<Othercoll> {

    Othercoll beginfandmasterid(Integer masterId, String date);

    Othercoll fandmasterid(Integer masterId, String date);
}
