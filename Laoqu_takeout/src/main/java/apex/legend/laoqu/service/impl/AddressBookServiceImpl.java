package apex.legend.laoqu.service.impl;

import apex.legend.laoqu.entity.AddressBook;
import apex.legend.laoqu.mapper.AddressBookMapper;
import apex.legend.laoqu.service.AddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
