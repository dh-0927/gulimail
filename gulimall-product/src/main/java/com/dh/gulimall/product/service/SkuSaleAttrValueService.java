package com.dh.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dh.common.utils.PageUtils;
import com.dh.gulimall.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-14 15:54:21
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

