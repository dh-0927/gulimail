package com.dh.gulimall.order.dao;

import com.dh.gulimall.order.entity.RefundInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 * 
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 15:49:44
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {
	
}
