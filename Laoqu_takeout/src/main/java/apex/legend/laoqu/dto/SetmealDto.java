package apex.legend.laoqu.dto;


import apex.legend.laoqu.entity.Setmeal;
import apex.legend.laoqu.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
