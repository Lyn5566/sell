package com.lyn.service;

import com.lyn.dataobject.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    ProductCategory findByCategoryId(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer>categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
