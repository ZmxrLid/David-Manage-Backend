package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Kaoqin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-13
 */
public interface IKaoqinService extends IService<Kaoqin> {

    Kaoqin getforhave(Integer teacherId, String date);

    Double getnumbyteacherid(Integer teacherId, String date);

}
