package com.dh.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dh.common.utils.PageUtils;
import com.dh.common.utils.Query;
import com.dh.gulimall.product.dao.CategoryBrandRelationDao;
import com.dh.gulimall.product.entity.BrandEntity;
import com.dh.gulimall.product.entity.CategoryBrandRelationEntity;
import com.dh.gulimall.product.entity.CategoryEntity;
import com.dh.gulimall.product.service.BrandService;
import com.dh.gulimall.product.service.CategoryBrandRelationService;
import com.dh.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryBrandRelationEntity> getCatelog(Long brandId) {
        LambdaQueryWrapper<CategoryBrandRelationEntity> lqw = new LambdaQueryWrapper<>();

        lqw.eq(CategoryBrandRelationEntity::getBrandId, brandId);

        return list(lqw);


    }

    @Override
    public void saveRelation(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        BrandEntity brand = brandService.getById(brandId);
        CategoryEntity category = categoryService.getById(catelogId);
        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelation.setCatelogName(category.getName());

        save(categoryBrandRelation);
    }

    @Override
    public void updateBrandName(Long brandId, String brandName) {
        LambdaUpdateWrapper<CategoryBrandRelationEntity> lqw = new LambdaUpdateWrapper<>();

        lqw.set(CategoryBrandRelationEntity::getBrandName, brandName)
                .eq(CategoryBrandRelationEntity::getBrandId, brandId);

        update(lqw);
    }

    @Override
    public void updateCategoryName(Long catId, String name) {
        baseMapper.updateCategoryName(catId, name);
    }

}