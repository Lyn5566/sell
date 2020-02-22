package com.lyn.service.impl;

import com.lyn.dataobject.ProductInfo;
import com.lyn.enums.ProductStatusEnum;
import com.lyn.service.ProductService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    void findOne() {
        ProductInfo one = productService.findOne("123456");
        Assertions.assertNotNull(one);
    }

    @Test
    void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assertions.assertNotEquals(0,productInfoList.size());
    }

    @Test
    void findAll() {
        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        Assertions.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123460");
        productInfo.setProductName("臭豆腐");
        productInfo.setProductPrice(new BigDecimal(4.3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很臭的豆腐");
        productInfo.setProductIcon("http://xxx.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assertions.assertNotNull(result);
    }
    @Test
    public void onSale(){
        ProductInfo productInfo = productService.findOne("123456");
        Assertions.assertEquals(ProductStatusEnum.UP.getCode(),productInfo.getProductStatus());
    }
    @Test
    public void offSale(){
        ProductInfo productInfo = productService.findOne("123457");
        Assertions.assertEquals(ProductStatusEnum.DOWN.getCode(),productInfo.getProductStatus());
    }
}