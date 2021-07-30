package cn.homyit.accessadmin.service.impl;

import cn.homyit.accessadmin.entity.Customer;
import cn.homyit.accessadmin.mapper.CustomerMapper;
import cn.homyit.accessadmin.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author Ziqiang CAO
 * @since 2021-07-28
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
