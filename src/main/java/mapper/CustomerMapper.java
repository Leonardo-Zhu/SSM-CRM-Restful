package mapper;

import model.customer.BaseDict;
import model.customer.Customer;
import model.customer.QueryVo;

import java.util.List;

public interface CustomerMapper {

    List<BaseDict> findDictByCode(String code);

    List<Customer> findCustomerByVo(QueryVo vo);
}
