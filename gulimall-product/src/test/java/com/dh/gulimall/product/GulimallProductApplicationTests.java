package com.dh.gulimall.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dh.gulimall.product.entity.BrandEntity;
import com.dh.gulimall.product.service.BrandService;
import com.dh.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@Slf4j
class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

//    @Autowired(required = false)
//    private OSSClient ossClient;
//
//
//    @Test
//    void testOss() throws IOException {
//        InputStream inputStream = Files.newInputStream(Paths.get("C:\\Users\\29235\\Pictures\\笨蛋\\Cache_-7b33bf89083fed93..jpg"));
//        ossClient.putObject("gulimall-denghui", "baobao.jpg", inputStream);
//        System.out.println("上传完成");
//    }

    @Test
    void testFindCompletePath() {
        Long[] paths = categoryService.findCompletePath(166L);
        log.info("查找到完整路径：{}", Arrays.asList(paths));
    }

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
