package com.lyn.form;

import com.lyn.enums.ProductStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductForm {
    private String productId;
    //商品名字
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图片
    private String productIcon;
    //商品状态
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //类目编号
    private Integer categoryType;
}
