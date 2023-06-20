package com.dh.gulimall.coupon.controller;

import com.dh.common.utils.PageUtils;
import com.dh.common.utils.R;
import com.dh.gulimall.coupon.entity.CouponEntity;
import com.dh.gulimall.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 优惠券信息
 *
 * @author denghui
 * @email dengh9098@gmail.com
 * @date 2023-06-15 19:59:07
 */
@RestController
@RefreshScope
@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private Integer age;

    @RequestMapping("/testConfig")
    public R testConfig() {
        System.out.println("用户名：" + name + "\t年龄：" + age);
        return R.ok().put("用户名", name).put("年龄", age);
    }


    @RequestMapping("/member/list")
    public R memberCoupon() {
        CouponEntity couponEntity1 = new CouponEntity();
        couponEntity1.setCouponName("满1000减1");
        CouponEntity couponEntity2 = new CouponEntity();
        couponEntity2.setCouponName("满20减18");
        return R.ok().put("coupons", Arrays.asList(couponEntity1, couponEntity2));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
