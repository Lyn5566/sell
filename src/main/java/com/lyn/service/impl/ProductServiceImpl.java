package com.lyn.service.impl;

import com.lyn.dataobject.ProductInfo;
import com.lyn.dto.CartDTO;
import com.lyn.enums.ProductStatusEnum;
import com.lyn.enums.ResultEnum;
import com.lyn.exception.SellException;
import com.lyn.repository.ProductInfoRepository;
import com.lyn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> productResult = repository.findById(productId);
        return productResult.get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cart : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cart.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.get().getProductStock() + cart.getProductQuantity();
            productInfo.get().setProductStock(result);
            repository.save(productInfo.get());
        }
    }

    @Override
    @Transactional
    /**
     * 减库存
     */
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cart : cartDTOList) {
            //通过商品id查询该商品
            ProductInfo productInfo = repository.findById(cart.getProductId()).orElse(null);
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cart.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    /**
     * 更新商品上架状态
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInfo onSale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum().equals(ProductStatusEnum.UP)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.get().setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo.get());
    }

    /**
     * 更新商品下架状态
     *
     * @param productId
     * @return
     */
    @Override
    public ProductInfo offSale(String productId) {
        Optional<ProductInfo> productInfo = repository.findById(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.get().getProductStatusEnum().equals(ProductStatusEnum.DOWN)) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.get().setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo.get());
    }
}
