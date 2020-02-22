package com.lyn.repository;

import com.lyn.dataobject.OrderMaster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    private final static String OPENID = "110110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("李四");
        orderMaster.setBuyerPhone("13899996767");
        orderMaster.setBuyerAddress("天津市");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assertions.assertNotNull(result);
    }
    @Test
    void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderMaster> PageResult = orderMasterRepository.findByBuyerOpenid(OPENID, request);
        Assertions.assertNotEquals(0,PageResult.getTotalElements());
    }
}