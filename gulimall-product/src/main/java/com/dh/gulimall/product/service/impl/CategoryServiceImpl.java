package com.dh.gulimall.product.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dh.common.utils.PageUtils;
import com.dh.common.utils.Query;
import com.dh.gulimall.product.dao.CategoryDao;
import com.dh.gulimall.product.entity.CategoryEntity;
import com.dh.gulimall.product.service.CategoryBrandRelationService;
import com.dh.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Lazy
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 先从数据库中查出所有的category
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);
        // 使用stream流
        // 过滤出所有父id为0的分类（顶级分类）
        // 递归找出过滤后的分类下的所有子分类并封装进实体的children集合
        // 排序
        // 收集
        // 返回
        return categoryEntityList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, categoryEntityList)))
                .sorted(Comparator.comparingInt(o -> (o.getSort() != null ? o.getSort() : 0)))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        // TODO: 检查当前删除的菜单，是否被别的地方引用
        // TODO: 检查是否包含子菜单
        // TODO: 检查需要删除的菜单是否存在
        baseMapper.deleteBatchIds(catIds);

    }

    @Override
    public Long[] findCompletePath(Long catelogId) {

        List<Long> paths = new ArrayList<>();
        findParent(catelogId, paths);

        Collections.reverse(paths);


        return paths.toArray(new Long[paths.size()]);

    }

    @Override
    @Transactional
    public void updateCascade(CategoryEntity category) {

        updateById(category);
        if (StringUtils.isNotBlank(category.getName())) {
            categoryBrandRelationService.updateCategoryName(category.getCatId(), category.getName());
        }
    }
    public void findParent(Long catelogId, List<Long> paths) {
        paths.add(catelogId);
        Long parentCid = getById(catelogId).getParentCid();
        if (parentCid == 0) {
            return;
        }
        findParent(parentCid, paths);

    }


    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, all)))
                .sorted(Comparator.comparingInt(o -> (o.getSort() != null ? o.getSort() : 0)))
                .collect(Collectors.toList());
    }

}