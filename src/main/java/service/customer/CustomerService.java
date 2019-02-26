package service.customer;

import model.customer.BaseDict;
import model.customer.Customer;
import model.customer.QueryVo;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    List<BaseDict> findDictByCode(String code);

    Map<String,List<Customer>> findCustomerByVo(QueryVo vo);

}
