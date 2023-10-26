package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.common.CustomException;
import apex.legend.laoqu.dto.SetmealDto;
import apex.legend.laoqu.entity.Setmeal;
import apex.legend.laoqu.entity.SetmealDish;
import apex.legend.laoqu.mapper.SetmealMapper;
import apex.legend.laoqu.service.SetMealDishService;
import apex.legend.laoqu.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetMealDishService setMealDishService;
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐基本信息，执行insert操作
        this.save(setmealDto);
        //保存套餐和菜品的关联关系
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setMealDishService.saveBatch(setmealDishes);
    }
    //删除套餐同时删除对应的菜品
    @Override
    public void removeWithDish(List<Long> ids) {
        //查询状态，确定是否删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);
        //如果不能删除，抛出业务异常
        if(count>0){
            throw new CustomException("套餐正在售卖中");
        }
        //可以删除，删除setmeal
        this.removeByIds(ids);
        //删除关系表
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);

        setMealDishService.remove(lambdaQueryWrapper);

    }


}
