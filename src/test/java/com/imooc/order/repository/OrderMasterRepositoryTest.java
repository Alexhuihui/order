package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.domain.OrderMaster;
import com.imooc.order.enums.OrderStatus;
import com.imooc.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


/**
 * @author 汪永晖
 */
@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setBuyerAddress("beijing");
        orderMaster.setBuyerName("alex");
        orderMaster.setBuyerOpenid("123456");
        orderMaster.setBuyerPhone("17770095789");
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }
}