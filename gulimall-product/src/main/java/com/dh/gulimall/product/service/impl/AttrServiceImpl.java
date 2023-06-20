package com.dh.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dh.common.utils.PageUtils;
import com.dh.common.utils.Query;
import com.dh.gulimall.product.dao.AttrDao;
import com.dh.gulimall.product.dto.AttrDto;
import com.dh.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.dh.gulimall.product.entity.AttrEntity;
import com.dh.gulimall.product.service.AttrAttrgroupRelationService;
import com.dh.gulimall.product.service.AttrGroupService;
import com.dh.gulimall.product.service.AttrService;
import com.dh.gulimall.product.service.CategoryService;
import com.dh.gulimall.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void saveAttr(AttrVo attrVo) {
        // 先保存属性基本信息
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);

        save(attrEntity);

        // 再保存关联关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationEntity.setAttrGroupId(attrVo.getAttrGroupId());
        attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        LambdaQueryWrapper<AttrEntity> lqw1 = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        lqw1.eq(catelogId != 0, AttrEntity::getCatelogId, catelogId)
                .and(StringUtils.isNotBlank(key), w -> {
                    w.eq(AttrEntity::getAttrId, key).or().like(AttrEntity::getAttrName, key);
                });

        IPage<AttrEntity> page =
                this.page(new Query<AttrEntity>().getPage(params), lqw1);

        List<AttrEntity> records = page.getRecords();
        List<AttrDto> attrDtoList = records.stream()
                .map(attrEntity -> {
                    // 得到分类名称
                    String attrGroupName = null;
                    Long categoryId = attrEntity.getCatelogId();
                    String categoryName = categoryService.getById(categoryId).getName();

                    Long attrId = attrEntity.getAttrId();

                    LambdaQueryWrapper<AttrAttrgroupRelationEntity> lqw2 = new LambdaQueryWrapper<>();
                    lqw2.eq(attrId != null, AttrAttrgroupRelationEntity::getAttrId, attrEntity.getAttrId());
                    AttrAttrgroupRelationEntity attrgroupRelation = attrAttrgroupRelationService.getOne(lqw2);

                    Long attrGroupId = attrgroupRelation.getAttrGroupId();

                    if (attrGroupId != null) {
                        attrGroupName = attrGroupService.getById(attrGroupId).getAttrGroupName();
                    }


                    return new AttrDto(attrEntity, categoryName, attrGroupName);
                }).collect(Collectors.toList());

//        page.setRecords(attrDtoList);
        IPage<AttrDto> pages = new Page<>();
        BeanUtils.copyProperties(page, pages, "records");
        pages.setRecords(attrDtoList);

        return new PageUtils(pages);
    }

}