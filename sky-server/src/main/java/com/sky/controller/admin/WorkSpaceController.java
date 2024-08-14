package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.WorkSpaceService;
import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@Slf4j
@RequestMapping("admin/workspace")
@Api(tags = "工作台相关")
public class WorkSpaceController {


    @Autowired
    private WorkSpaceService workSpaceService;


    /**
     * 查询今日运营数据
     * @return
     */
    @GetMapping("/businessData")
    @ApiOperation("工作台今日数据查询")
    public Result<BusinessDataVO> businessData(){
        //获得当日开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = workSpaceService.getBusinessData(begin,end);
        return Result.success(businessDataVO);
    }


    /**
     * 查询订单管理数据
     * @return
     */
    @GetMapping("/overviewOrders")
    @ApiOperation("工作台订单管理数据查询")
    public Result<OrderOverViewVO> overviewOrders(){
        //获得当日开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        OrderOverViewVO orderOverViewVO = workSpaceService.getOverviewOrders(begin,end);

        return Result.success(orderOverViewVO);
    }


    /**
     * 查询菜品总览
     * @return
     */
    @GetMapping("/overviewDishes")
    @ApiOperation("工作台菜品数据查询")
    public Result<DishOverViewVO> overviewDishes(){
        DishOverViewVO dishOverViewVO = workSpaceService.getOverviewDishes();
        return Result.success(dishOverViewVO);
    }


    /**
     * 查询套餐总览
     * @return
     */
    @GetMapping("/overviewSetmeals")
    @ApiOperation("工作台套餐数据查询")
    public Result<SetmealOverViewVO> overviewSetmeals(){
        SetmealOverViewVO setmealOverViewVO = workSpaceService.getOverviewSetmeals();
        return Result.success(setmealOverViewVO);
    }



}
