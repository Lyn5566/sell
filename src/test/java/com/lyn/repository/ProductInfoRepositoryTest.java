package com.lyn.repository;

import com.lyn.dataobject.ProductInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("麻辣烫");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxx.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo result = repository.save(productInfo);
        Assertions.assertNotNull(result);
    }
    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assertions.assertNotEquals(0,productInfoList.size());
    }

}