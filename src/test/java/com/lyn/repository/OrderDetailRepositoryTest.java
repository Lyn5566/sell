package com.lyn.repository;

import com.lyn.dataobject.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    void findByOrderId() {
        List<OrderDetail> orderList = orderDetailRepository.findByOrderId("1111112");
        Assertions.assertNotEquals(0,orderList.size());

    }
    @Test
    public void saveTest(){
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setDetailId("12345789");
    orderDetail.setOrderId("1111115");
    orderDetail.setProductId("1111126");
    orderDetail.setProductName("麻辣鸭脖");
    orderDetail.setProductPrice(new BigDecimal(5.3));
    orderDetail.setProductIcon("http://xxxx.jpg");
    orderDetail.setProductQuantity(2);
        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(result);

    }
}