package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.common.CustomException;
import apex.legend.laoqu.entity.Category;
import apex.legend.laoqu.entity.Dish;
import apex.legend.laoqu.entity.Setmeal;
import apex.legend.laoqu.mapper.CategoryMapper;
import apex.legend.laoqu.service.CategoryService;
import apex.legend.laoqu.service.DishService;
import apex.legend.laoqu.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public void remove(Long id) {
        //查询分类是否关联菜品或者套餐，如果关联。抛出异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int dishCount =dishService.count(dishLambdaQueryWrapper);
        if(dishCount>0){
            throw new CustomException("当前分类项关联菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int setMealCount =setmealService.count(setmealLambdaQueryWrapper);
        if(setMealCount>0){
            throw new CustomException("当前分类项关联套餐，不能删除");
        }
        super.removeById(id);
    }
}
