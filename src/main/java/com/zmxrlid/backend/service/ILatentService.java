package com.zmxrlid.backend.service;

import com.zmxrlid.backend.entity.Latent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 惜喃
 * @since 2025-08-02
 */
public interface ILatentService extends IService<Latent> {

    List<Latent> fromname(String teacherName, String date);

    List<Latent> telname(String teacherName, String date);

    List<Latent> teachername(String teacherName, String date);

    List<Latent> talkname(String teacherName, String date);

    List<Latent> speech(String teacherName, String date);
}
