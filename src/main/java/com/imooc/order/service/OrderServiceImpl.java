package com.imooc.order.service;

import com.imooc.order.client.ProductClient;
import com.imooc.order.domain.OrderDetail;
import com.imooc.order.domain.OrderMaster;
import com.imooc.order.domain.ProductInfo;
import com.imooc.order.dto.CartDTO;
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
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 汪永晖
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDTO OrderDetail和OrderMaster的结合体
     * @return
     */
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfo> productInfoList = productClient.listForOrder(productIdList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //单价乘以数量

                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);

                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();

        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
