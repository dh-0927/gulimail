package com.dh.gulimall.member.dao;

import com.dh.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 20:05:55
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
