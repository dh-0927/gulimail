package com.dh.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dh.common.utils.PageUtils;
import com.dh.gulimall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-14 15:54:21
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

