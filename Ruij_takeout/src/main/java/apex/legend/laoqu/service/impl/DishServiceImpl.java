package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.dto.DishDto;
import apex.legend.laoqu.entity.Dish;
import apex.legend.laoqu.entity.DishFlavor;
import apex.legend.laoqu.mapper.DishMapper;
import apex.legend.laoqu.service.DishFlavorService;
import apex.legend.laoqu.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品基本信息
        this.save(dishDto);
        //保存菜品口味信息
        Long dishId = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item)->{
           item.setDishId(dishId);
           return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
