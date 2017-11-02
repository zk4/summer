package org.smart4j.chapter1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.util.DataBaseHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by zk on 01/11/2017.
 */
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getCustomerList() {

        List<Customer> customers1;
        String sql = "select * from customer";
        customers1 = DataBaseHelper.queryEntityList(Customer.class, sql);
        return customers1;
    }


    public Customer getCustomer(Long id) {

        return DataBaseHelper.getOneEntity(Customer.class,id);
    }



    public boolean createCustomer(Map<String, Object> fieldMap) {

        return DataBaseHelper.insertEntity(Customer.class, fieldMap);
    }

    public boolean updateCustomer(Long id, Map<String, Object> fieldMap) {
        //todo
        return DataBaseHelper.updateEntity(Customer.class, id,fieldMap);
    }

    public boolean deleteCustomer(Long id) {

        return DataBaseHelper.deleteEntity(Customer.class,id);
    }
}
