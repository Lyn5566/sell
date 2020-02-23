package com.lyn.repository;

import com.lyn.dataobject.SellerInfo;
import com.lyn.utils.KeyUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.getUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc123");
        SellerInfo result = sellerInfoRepository.save(sellerInfo);
        Assertions.assertNotNull(result);
    }
    @Test
    void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid("abc123");
        Assertions.assertNotNull(sellerInfo);
    }
}