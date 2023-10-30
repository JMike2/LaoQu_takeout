package apex.legend.laoqu.service;

import apex.legend.laoqu.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrdersService extends IService<Orders> {

    public void submit(Orders orders);
}
