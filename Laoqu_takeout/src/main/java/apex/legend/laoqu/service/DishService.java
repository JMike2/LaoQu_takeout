package apex.legend.laoqu.service;

import apex.legend.laoqu.dto.DishDto;
import apex.legend.laoqu.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
    //新增菜品，同时插入菜品的口味数据
    public void saveWithFlavor(DishDto dishDto);
    public DishDto getByIdWithFlavor(Long id);
    public void updateWithFlavor(DishDto dishDto);
}
