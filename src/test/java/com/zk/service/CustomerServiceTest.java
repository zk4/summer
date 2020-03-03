package com.zk.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.zk.model.Customer;
import org.summer.framework.util.DataBaseHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zk on 01/11/2017.
 */
public class CustomerServiceTest {


    @Before
    public void setUp() throws Exception {
        DataBaseHelper.executeSqlFile("sql/customer_init.sql");
    }

    private final CustomerService customerService;

    public CustomerServiceTest() {
        this.customerService =new  CustomerService();
    }

    @Test
    public void getCustomerList() throws Exception {
        List<Customer> customerList = customerService.getCustomerList();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        Assert.assertEquals(3,customerList.size());
    }

    @Test
    public void getCustomer() throws Exception {
        List<Customer> customerList = customerService.getCustomerList();
        Customer customer = customerService.getCustomer(customerList.get(0).getId());
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomer() throws Exception {

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id",102L);
        stringObjectHashMap.put("name","customer100");
        stringObjectHashMap.put("contact","john");
        stringObjectHashMap.put("telephone","12345678");
        boolean customer = customerService.createCustomer(stringObjectHashMap);
        Assert.assertTrue(customer);

        DataBaseHelper.rollback();
    }


    @Test
    public void updateCustomer() throws Exception {
        List<Customer> customerList = customerService.getCustomerList();

        HashMap<String, Object> filedMap = new HashMap<>();
        filedMap.put("contact","Eric");
        boolean b = customerService.updateCustomer(customerList.get(0).getId(), filedMap);
        Assert.assertTrue(b);
    }

    @Test
    public void deleteCustomer() throws Exception {
        List<Customer> customerList = customerService.getCustomerList();
        boolean b = customerService.deleteCustomer(customerList.get(0).getId());
        Assert.assertTrue(b);
    }

}