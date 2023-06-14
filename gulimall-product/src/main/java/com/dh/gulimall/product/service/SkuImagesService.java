package com.dh.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dh.common.utils.PageUtils;
import com.dh.gulimall.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-14 15:54:21
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

