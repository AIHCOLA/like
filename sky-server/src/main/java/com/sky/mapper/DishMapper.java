package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {


    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     */
    @Insert("insert into dish(name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) " +
            "VALUE" +
            "(#{name},#{categoryId},#{price},#{image},#{description},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     */
    Page<DishVO> DishPage(DishPageQueryDTO dishPageQueryDTO);


    /**
     * 根据主键删除菜品
     * @param id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);


    /**
     * 根据主键查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 修改菜品
     * @param dish
     */
    void update(Dish dish);

    /**
     * 新增套餐、根据分类id查询菜品
     * @param dish
     */
    List<Dish> getByCategoryId(Dish dish);


    /**
     * 条件查询对应菜品和口味
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 根据条件统计菜品数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
