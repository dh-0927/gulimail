package com.dh.gulimall.order.dao;

import com.dh.gulimall.order.entity.OrderSettingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息
 * 
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 15:49:44
 */
@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSettingEntity> {
	
}
