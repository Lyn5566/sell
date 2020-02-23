package com.lyn.controller;

import com.lly835.bestpay.rest.type.Post;
import com.lyn.dataobject.ProductCategory;
import com.lyn.dataobject.ProductInfo;
import com.lyn.dto.OrderDTO;
import com.lyn.exception.SellException;
import com.lyn.form.ProductForm;
import com.lyn.service.CategoryService;
import com.lyn.service.ProductService;
import com.lyn.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 查询商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "4") Integer size,
                             Map<String,Object> map){
        PageRequest request = PageRequest.of(page-1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        return new ModelAndView("product/list",map);

    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId")String productId,
                               Map<String,Object>map){
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error");
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success");
    }

    /**
     * 跳转到更新或新增页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }

    /**
     * 更新或新增
     * @param form
     * @param result
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult result,
                             Map<String, Object> map) {
        if (result.hasErrors()) {
            map.put("msg", result.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        if (!StringUtils.isEmpty(form.getProductId())) {
            productInfo = productService.findOne(form.getProductId());
        } else {
            form.setProductId(KeyUtil.getUniqueKey());
        }
        BeanUtils.copyProperties(form, productInfo);
        productService.save(productInfo);
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }
}
