package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result addDish(@RequestBody DishDTO dishDTO){
        log.info("新增菜品，参数为：{}", dishDTO);
        dishService.addDish(dishDTO);
        //清理缓存数据
        String key = "dish" + dishDTO.getCategoryId();
        cleanCache(key);
        return Result.success();
    }

    /**
     * 新增套餐、根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(String categoryId){
        List<Dish> list = dishService.getByCategoryId(Long.valueOf(categoryId));
        return Result.success(list);
    }


    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询，参数为：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 批量删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result deleteBatch(@RequestParam List<Long> ids){
        log.info("菜品批量删除，参数为：{}",ids);
        dishService.deleteBatch(ids);

        //将所有菜品缓存数据清理，所有以dish开头的key
        cleanCache("dish_*");

        return Result.success();
    }




    /**
     * 修改菜品 1.通过id查询菜品以及对应的口味数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){//该处的id是指传进去的数据
        log.info("根据id查询菜品，参数为：{}", id);
        DishVO dishVO = dishService.getById(id);
        return Result.success(dishVO);//将获取到的数据返回到前端

    }


    /**
     * 修改菜品 2.传入数据
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        dishService.updateDish(dishDTO);

        //将所有菜品缓存数据清理，所有以dish开头的key
        cleanCache("dish_*");

        return Result.success();
    }


    /**
     * 启售停售菜品
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启售停售菜品")
    public Result saleOrStop(@PathVariable Integer status, Long id){
        log.info("启售停售菜品，参数为：{},{}",status,id);
        dishService.saleOrStop(status,id);

        //将所有菜品缓存数据清理，所有以dish开头的key
        cleanCache("dish_*");

        return Result.success();

    }

    /**
     * 清理缓存
     * @param pattern
     */
    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);

    }

}
