package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {


    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);


    /**
     * 订单支付
     * @param
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;


    /**
     * 业务处理，支付成功，修改订单状态、来单提醒
     */
    void paySuccess(String outTradeNo);


    /**
     * 用户端历史订单分页查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult PageQuery(int page, int pageSize, Integer status);


    /**
     * 查询订单详情（用户端和管理端）
     * @param id
     * @return
     */
    OrderVO details(Long id);


    /**
     * 取消订单
     * @param id
     */
    void cancelOrder(Long id) throws Exception;

    /**
     *再来一单
     * @param id
     */
    void oneMore(Long id);

    /**
     * 催单
     * @param id
     */
    void reminder(Long id);

    /**
     * 订单搜索
     * @param ordersPageQueryDTO
     * @return
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     * @return
     */
    OrderStatisticsVO statistics();



    /**
     * 接单
     * @param ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     * @param ordersRejectionDTO
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 取消订单
     * @param ordersCancelDTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单
     * @param id
     */
    void delivery(Long id);

    /**
     * 完成订单
     * @param id
     */
    void complete(Long id);
}
