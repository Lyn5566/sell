package com.lyn.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lyn.dto.OrderDTO;
import com.lyn.enums.PayStatusEnum;
import com.lyn.enums.ResultEnum;
import com.lyn.exception.SellException;
import com.lyn.service.OrderService;
import com.lyn.service.PayService;
import com.lyn.utils.JsonUtil;
import com.lyn.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信点餐】发起支付 payRequest={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse= bestPayService.pay(payRequest);
        log.info("【微信点餐】发起支付 payResponse={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信点餐】异步通知 payResponse={}",JsonUtil.toJson(payResponse));
        //修改订单支付状态
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人(下单人=支付人)
        //先查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        if(orderDTO == null){
            log.error("【微信点餐】异步通知 订单不存在,orderId={}",payResponse.getOrderId());
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(!MathUtil.equals(orderDTO.getOrderAmount().doubleValue(),payResponse.getOrderAmount())){
            log.error("【微信点餐】异步通知 订单金额不一致,orderId={},微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),payResponse.getOrderAmount(),orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR );
        }
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest request = new RefundRequest();
        request.setOrderId(orderDTO.getOrderId());
        request.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("微信点餐】退款 request={}",JsonUtil.toJson(request));
        RefundResponse refundResponse = bestPayService.refund(request);
        log.info("微信点餐】退款 response={}",JsonUtil.toJson(refundResponse));

        return refundResponse;

    }
}
