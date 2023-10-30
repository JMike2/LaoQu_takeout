package apex.legend.laoqu.controller;

import apex.legend.laoqu.common.R;
import apex.legend.laoqu.dto.SetmealDto;
import apex.legend.laoqu.entity.Category;
import apex.legend.laoqu.entity.Setmeal;
import apex.legend.laoqu.service.CategoryService;
import apex.legend.laoqu.service.SetMealDishService;
import apex.legend.laoqu.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetMealDishService setMealDishService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("添加成功");
    }

    //修改套餐状态
    @PostMapping("/status/0")
    public R<String> change(Long ids){
        Setmeal setmeal = setmealService.getById(ids);
        setmeal.setStatus(0);
        setmealService.removeById(ids);
        setmealService.save(setmeal);
        return R.success("禁用成功");
    }
    /*
    *套餐信息分页查询
     */

    @GetMapping("/page")
    public R<Page> page(int page ,int pageSize,String name){
        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,queryWrapper);
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类名称
            Category category = categoryService.getById(categoryId);
            if(category!=null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return  setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids){
        setmealService.removeWithDish(ids);
        return R.success("删除成功");
    }
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
         LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(setmeal.getId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
         queryWrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
         queryWrapper.orderByDesc(Setmeal::getUpdateTime);
         List<Setmeal> list = setmealService.list(queryWrapper);
         return  R.success(list);
    }
}
