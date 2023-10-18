package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.Employee;
import apex.legend.laoqu.mapper.EmployeeMapper;
import apex.legend.laoqu.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
