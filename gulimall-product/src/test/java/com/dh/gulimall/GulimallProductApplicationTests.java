package com.dh.gulimall;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dh.gulimall.product.entity.BrandEntity;
import com.dh.gulimall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("爱国就买小华为");
        brandEntity.setName("华为荣耀");
        brandService.save(brandEntity);
        System.out.println("添加华为手机成功！");
    }

    @Test
    void testQuery() {
        LambdaQueryWrapper<BrandEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BrandEntity::getName, "华为荣耀");
        brandService.list(lqw).forEach(System.out::println);

//        brandService.list
    }

    @Test
    void testDelete() {
        if (brandService.removeByIds(Arrays.asList(2, 3))) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }


}
