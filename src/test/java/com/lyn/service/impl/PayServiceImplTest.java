package com.lyn.service.impl;
import com.lyn.dto.OrderDTO;
import com.lyn.service.OrderService;
import com.lyn.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    void create() {
        OrderDTO orderDTO = orderService.findOne("1581220389566194055");
        payService.create(orderDTO);
    }
    @Test
    void refund(){
        OrderDTO orderDTO = orderService.findOne("1581143696547404241");
        payService.refund(orderDTO);

    }
}