package com.niuxiaofei.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuxiaofei.dao.OrderMapper;
import com.niuxiaofei.entity.Order;
import com.niuxiaofei.entity.OrderInfo;
import com.niuxiaofei.entity.Product;
import com.niuxiaofei.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    private OrderMapper orderMapper;

    @Override
    public User login(String username) {
        return orderMapper.login(username);
    }

    @Override
    public PageInfo<Order> list(Order order, Integer pageNo, Integer pageSize) {
        //根据传过来的用户id查询用户信息
        User user = orderMapper.getUserById(order.getUser_id());
        if(user.getType()==0){
            //如果是普通用户,还根据本身id查找
            order.setUser_id(user.getId());
        }else {
            //如果是管理员,将用户id设置为0
            order.setUser_id(0);
        }
        //分页
        PageHelper.startPage(pageNo,pageSize);
        List<Order> list = orderMapper.list(order);
        return new PageInfo<>(list);
    }

    @Override
    public List<OrderInfo> orderInfo(Integer order_id) {
        return orderMapper.orderInfo(order_id);
    }

    @Override
    public List<Product> initData() {
        return orderMapper.initData();
    }

    @Override
    public boolean save(Order order) {
        //取对象中的集合
        List<Product> list = order.getProductsList();
        try {
            //添加订单
            orderMapper.saveOrder(order);
            list.forEach(product->{
                if(product.getNum()!=0){
                    //添加订单详情
                    orderMapper.save(order.getId(),product);
                }
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
