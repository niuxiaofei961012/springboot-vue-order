package com.niuxiaofei.controller;

import com.github.pagehelper.PageInfo;
import com.niuxiaofei.entity.Order;
import com.niuxiaofei.entity.OrderInfo;
import com.niuxiaofei.entity.Product;
import com.niuxiaofei.entity.User;
import com.niuxiaofei.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 登录
     * @param user
     * @return
     */
    @GetMapping("login")
    public Object login(User user){
        //定义map集合存放数据
        Map<String,Object> map = new HashMap<>();
        User loginUser = orderService.login(user.getUsername());
        //根据姓名如果没查到 证明此人不存在
        if(loginUser==null){
            map.put("nameMsg","用户名不存在");
        }else{
            //用户名存在判断密码
            if(loginUser.getPassword().equals(user.getPassword())){
                //密码匹配
                map.put("msg",true);
                //将用户信息返回前台
                map.put("user",loginUser);
            }else{
                map.put("pwdMsg","密码错误");
            }
        }
        return map;
    }

    /**
     * 列表 查询 分页
     * @param order
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("list")
    public PageInfo<Order> list(Order order,@Param("pageNo") @RequestParam(defaultValue = "1") Integer pageNo,
                                @Param("pageSize") @RequestParam(defaultValue = "3")Integer pageSize){


        return orderService.list(order,pageNo,pageSize);
    }
    //订单详情
    @GetMapping("orderInfo")
    public List<OrderInfo> orderInfo(Integer order_id){

        return orderService.orderInfo(order_id);
    }

    /**
     * 商品表信息
     * @return
     */
    @GetMapping("initData")
    public List<Product> initData(){
        return orderService.initData();
    }

    /**
     * 添加订单和订单详情
     * @param order
     * @return
     */
    @PostMapping("save")
    public boolean save(@RequestBody Order order){
        return orderService.save(order);
    }
}
