package com.dh.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dh.common.utils.PageUtils;
import com.dh.gulimall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-14 15:54:21
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

