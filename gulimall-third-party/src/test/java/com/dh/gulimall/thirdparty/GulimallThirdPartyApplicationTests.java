package com.dh.gulimall.thirdparty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class GulimallThirdPartyApplicationTests {

    @Test
    void contextLoads() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
    }

}
