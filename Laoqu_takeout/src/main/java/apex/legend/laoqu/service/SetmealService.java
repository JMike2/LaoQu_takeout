package apex.legend.laoqu.service;

import apex.legend.laoqu.dto.SetmealDto;
import apex.legend.laoqu.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface SetmealService extends IService<Setmeal> {

    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
