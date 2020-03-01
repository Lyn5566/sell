package com.lyn.controller;

import com.lyn.dto.OrderDTO;
import com.lyn.enums.ResultEnum;
import com.lyn.exception.SellException;
import com.lyn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 卖家端查询并渲染订单列表
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
        Page<OrderDTO> orderDTOPage = orderService.findAllList(request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                              Map<String,Object>map){

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端取消订单】订单不存在,orderId={}", orderId);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object>map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("【卖家端订单详情】订单不存在,orderId={}",orderId);
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO",orderDTO);
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object>map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【卖家端完结订单】订单不存在,orderId={}",orderId);
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);

    }
}
