package com.lyn.service.impl;

import com.lyn.dataobject.SellerInfo;
import com.lyn.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SellerServiceImplTest {
    @Autowired
    private SellerService sellerService;
    @Test
    void findSellerInfoByOpenId() {
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenId("abc123");
        Assertions.assertNotNull(sellerInfo);
    }
}