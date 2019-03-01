package com.imooc.order.service;

import com.imooc.order.dto.OrderDTO;

/**
 * @author 汪永晖
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO OrderDetail和OrderMaster的结合体
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
