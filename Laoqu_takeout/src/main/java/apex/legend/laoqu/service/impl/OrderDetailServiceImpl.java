package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.OrderDetail;
import apex.legend.laoqu.mapper.OrderDetailMapper;
import apex.legend.laoqu.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
