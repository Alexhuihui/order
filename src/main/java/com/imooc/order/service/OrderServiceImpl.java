package com.imooc.order.service;

import com.imooc.order.domain.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.OrderStatus;
import com.imooc.order.enums.PayStatusEnum;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author 汪永晖
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 创建订单
     *
     * @param orderDTO OrderDetail和OrderMaster的结合体
     * @return
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        //订单入库
        OrderMaster orderMaster = new OrderMaster();

        orderDTO.setOrderId(KeyUtil.genUniqueKey());

        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
