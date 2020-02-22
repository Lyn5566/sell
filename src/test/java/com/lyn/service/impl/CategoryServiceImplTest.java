package com.lyn.service.impl;

import com.lyn.dataobject.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryServiceImplTest {
@Autowired
private CategoryServiceImpl categoryService;
    @Test
    void findByCategoryId() {
        ProductCategory productCategory = categoryService.findByCategoryId(1);
        Assertions.assertEquals(1,productCategory.getCategoryId());
    }

    @Test
    void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assertions.assertNotEquals(0,all.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer>list = Arrays.asList(1,8);
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryTypeIn(list);
        Assertions.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory("男生最爱",1);
        ProductCategory result = categoryService.save(productCategory);
        Assertions.assertNotNull(result);
    }
}