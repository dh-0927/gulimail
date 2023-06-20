package com.dh.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dh.common.utils.PageUtils;
import com.dh.common.utils.Query;
import com.dh.gulimall.product.dao.AttrGroupDao;
import com.dh.gulimall.product.entity.AttrGroupEntity;
import com.dh.gulimall.product.service.AttrGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {

        LambdaQueryWrapper<AttrGroupEntity> lqw = new LambdaQueryWrapper<>();
        String key = (String) params.get("key");

        lqw.eq(catelogId != 0, AttrGroupEntity::getCatelogId, catelogId)
                .and(StringUtils.isNotBlank(key), w -> {
                    w.eq(AttrGroupEntity::getAttrGroupId, key).or().like(AttrGroupEntity::getAttrGroupName, key);
                });

        IPage<AttrGroupEntity> page =
                this.page(new Query<AttrGroupEntity>().getPage(params), lqw);


        return new PageUtils(page);
    }

}