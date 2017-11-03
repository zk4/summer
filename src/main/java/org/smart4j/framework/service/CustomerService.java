package org.smart4j.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.model.Customer;
import org.smart4j.framework.util.DataBaseHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by zk on 01/11/2017.
 */
@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Transaction
    public List<Customer> getCustomerList() {

        List<Customer> customers1;
        String sql = "select * from customer";
        customers1 = DataBaseHelper.queryEntityList(Customer.class, sql);
        return customers1;
    }

    @Transaction
    public Customer getCustomer(Long id) {

        return DataBaseHelper.getOneEntity(Customer.class,id);
    }


    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap) {

        return DataBaseHelper.insertEntity(Customer.class, fieldMap);
    }
    @Transaction
    public boolean updateCustomer(Long id, Map<String, Object> fieldMap) {
        //todo
        return DataBaseHelper.updateEntity(Customer.class, id,fieldMap);
    }
    @Transaction
    public boolean deleteCustomer(Long id) {

        return DataBaseHelper.deleteEntity(Customer.class,id);
    }
}
