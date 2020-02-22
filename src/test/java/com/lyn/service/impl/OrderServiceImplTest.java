package com.lyn.service.impl;

import com.lyn.dataobject.OrderDetail;
import com.lyn.dto.OrderDTO;
import com.lyn.enums.OrderStatusEnum;
import com.lyn.enums.PayStatusEnum;
import com.lyn.enums.ResultEnum;
import com.lyn.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String BUYER_OPENID = "110110";
    private final String ORDER_ID = "123456";
    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("125125");
        orderDTO.setBuyerName("马化腾");
        orderDTO.setBuyerPhone("13122335566");
        orderDTO.setBuyerAddress("深圳");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> o1 = new ArrayList();

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(2);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId("123458");
        orderDetail2.setProductQuantity(5);

        o1.add(orderDetail1);
        o1.add(orderDetail2);

        orderDTO.setOrderDetailList(o1);
        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result={}" + result);

    }

    @Test
    void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查找单个订单】 result={}" + result);
        Assertions.assertNotNull(result);
    }

    @Test
    void findList() {
        PageRequest request = PageRequest.of(0, 3);
        Page<OrderDTO> result = orderService.findList(BUYER_OPENID, request);
        Assertions.assertNotEquals(0,result.getTotalElements());
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assertions.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assertions.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assertions.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void findAllList(){
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<OrderDTO> allList = orderService.findAllList(pageRequest);
        Assertions.assertTrue(allList.getTotalElements() >0,"查询所有订单");
    }
}