package com.dh.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dh.common.utils.PageUtils;
import com.dh.common.utils.Query;
import com.dh.gulimall.product.dao.BrandDao;
import com.dh.gulimall.product.entity.BrandEntity;
import com.dh.gulimall.product.service.BrandService;
import com.dh.gulimall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    @Lazy
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        LambdaQueryWrapper<BrandEntity> lqw = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        lqw.like(StringUtils.isNotBlank(key), BrandEntity::getDescript, key)
                .or()
                .like(StringUtils.isNotBlank(key), BrandEntity::getName, key);


        IPage<BrandEntity> page =
                this.page(new Query<BrandEntity>().getPage(params), lqw);



        return new PageUtils(page);
    }

    // 更新品牌名的时候同时更新品牌关联分类表
    @Override
    @Transactional
    public void updateCascade(BrandEntity brand) {
        updateById(brand);
        if (StringUtils.isNotBlank(brand.getName())) {
            categoryBrandRelationService.updateBrandName(brand.getBrandId(), brand.getName());
            // TODO 更新其他关联
        }
    }

}