package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {



    /**
     * 新增套餐
     * @param setmealDTO
     */
    void addSetmeal(SetmealDTO setmealDTO);


    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult page (SetmealPageQueryDTO setmealPageQueryDTO);


    /**
     * 修改套餐
     * 1.根据id查询套餐，将要修改的套餐数据回显到表单
     * @param id
     * @return
     */
    SetmealVO getById(Long id);


    /**
     * 修改套餐
     * 2.输入数据
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 套餐的停售启售
     * @param status
     * @param id
     */
    void StartOrStop(Integer status, Long id);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询包含的菜品列表
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
