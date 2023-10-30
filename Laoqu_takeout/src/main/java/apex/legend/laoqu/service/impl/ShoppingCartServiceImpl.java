package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.ShoppingCart;
import apex.legend.laoqu.mapper.ShoppingCartMapper;
import apex.legend.laoqu.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
