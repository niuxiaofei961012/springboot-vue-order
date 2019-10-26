package com.niuxiaofei.service;

import com.github.pagehelper.PageInfo;
import com.niuxiaofei.entity.Order;
import com.niuxiaofei.entity.OrderInfo;
import com.niuxiaofei.entity.Product;
import com.niuxiaofei.entity.User;

import java.util.List;

public interface OrderService {
    /**
     * 登录
     *
     * @param username
     * @return
     */
    User login(String username);

    /**
     * 查询 列表 分页
     *
     * @param order
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Order> list(Order order, Integer pageNo, Integer pageSize);

    /**
     * 订单详情
     *
     * @param order_id
     * @return
     */
    List<OrderInfo> orderInfo(Integer order_id);

    /**
     * 商品信息
     * @return
     */
    List<Product> initData();

    /**
     * 保存
     * @param list
     * @return
     */
    boolean save(Order order);
}