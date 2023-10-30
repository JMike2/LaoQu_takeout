package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.User;
import apex.legend.laoqu.mapper.UserMapper;
import apex.legend.laoqu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
