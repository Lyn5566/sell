package com.lyn.repository;

import com.lyn.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    //通过categoryType来查询整个category
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
