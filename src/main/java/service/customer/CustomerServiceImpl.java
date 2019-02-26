package service.customer;

import mapper.CustomerMapper;
import model.customer.BaseDict;
import model.customer.Customer;
import model.customer.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;

    @Override
    public List<BaseDict> findDictByCode(String code) {
        return customerMapper.findDictByCode(code);
    }

    @Override
    public Map<String, List<Customer>> findCustomerByVo(QueryVo vo) {
        Map<String,List<Customer>> map = new HashMap<>();
        map.put("data",customerMapper.findCustomerByVo(vo));
        return map;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
}
