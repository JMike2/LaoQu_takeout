package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.Dish;
import apex.legend.laoqu.mapper.DishMapper;
import apex.legend.laoqu.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
