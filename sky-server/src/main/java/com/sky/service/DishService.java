package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {


    /**
     * 新增菜品
     * @param dishDTO
     */
    void addDish(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);


    /**
     * 修改菜品 1.通过id查询菜品以及对应的口味数据
     * @param id
     * @return
     */
    DishVO getById(Long id);


    /**
     * 修改菜品 2.传入数据
     * @param dishDTO
     */
    void updateDish(DishDTO dishDTO);


    /**
     * 启售停售菜品
     * @param status
     * @param id
     */
    void saleOrStop(Integer status, Long id);

    /**
     * 新增套餐、根据分类id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> getByCategoryId(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
