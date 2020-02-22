package com.lyn.coverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lyn.dataobject.OrderDetail;
import com.lyn.dto.OrderDTO;
import com.lyn.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail>orderDetailList = new ArrayList<>();
         orderDetailList = gson.fromJson(orderForm.getItems()
                , new TypeToken<List<OrderDetail>>() {
                }.getType());
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
