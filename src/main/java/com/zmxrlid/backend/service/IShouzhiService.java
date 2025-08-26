package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Shouzhi;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-15
 */
public interface IShouzhiService extends IService<Shouzhi> {


    Shouzhi getforhave(Integer masterId, String date);
}
