package com.dh.gulimall.coupon.dao;

import com.dh.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 19:59:07
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
