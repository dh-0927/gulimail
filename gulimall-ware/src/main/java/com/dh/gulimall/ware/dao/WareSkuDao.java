package com.dh.gulimall.ware.dao;

import com.dh.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 20:13:47
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
