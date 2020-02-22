package com.lyn.service;

import com.lyn.dataobject.ProductInfo;
import com.lyn.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);
    /**
     * 查询在架商品
     */

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO>cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO>cartDTOList);
    //更新上架状态
    public ProductInfo onSale(String productId);
    //更新下架状态
    public ProductInfo offSale(String productId);
}
