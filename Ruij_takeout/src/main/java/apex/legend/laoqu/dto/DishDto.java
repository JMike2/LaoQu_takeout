package apex.legend.laoqu.dto;


import apex.legend.laoqu.entity.Dish;
import apex.legend.laoqu.entity.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
