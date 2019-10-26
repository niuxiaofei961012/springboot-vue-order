package com.niuxiaofei.dao;

import com.niuxiaofei.entity.Order;
import com.niuxiaofei.entity.OrderInfo;
import com.niuxiaofei.entity.Product;
import com.niuxiaofei.entity.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 登录
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    User login(String username);

    /**
     * 查询登录用户的信息
     * @return
     */
    @Select("select * from users where id=#{id}")
    User getUserById(Integer id);

    /**
     * 查询列表
     * @param order
     * @return
     */
    List<Order> list(Order order);
    /**
     * 订单详情
     *
     * @param order_id
     * @return
     */
    @Select("select a.order_id,a.product_id,a.num,a.total_price,b.`name`,c.create_time \n" +
            "from order_info a LEFT JOIN product b on a.product_id=b.id \n" +
            "LEFT JOIN t_order c on a.order_id=c.id\n" +
            "where order_id=#{order_id}")
    List<OrderInfo> orderInfo(Integer order_id);

    /**
     * 商品信息
     * @return
     */
    @Select("select * from product")
    List<Product> initData();

    /**
     * 添加订单和订单详情
     * @param id
     * @param product
     * @return
     */
    int save(@Param("order_id") Integer id, @Param("product") Product product);

    /**
     * 添加订单返回订单号
     * @param order
     * @return
     */
    int saveOrder(Order order);
}
