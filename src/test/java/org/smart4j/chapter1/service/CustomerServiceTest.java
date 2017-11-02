package org.smart4j.chapter1.service;

import org.junit.Assert;
import org.junit.Test;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.util.DataBaseHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zk on 01/11/2017.
 */
public class CustomerServiceTest {

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
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomer() throws Exception {
        Long id =1L;
        Customer customer = customerService.getCustomer(id);
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
        Long id = 100L;
        HashMap<String, Object> filedMap = new HashMap<>();
        filedMap.put("contact","Eric");
        boolean b = customerService.updateCustomer(id, filedMap);
        Assert.assertTrue(b);
    }

    @Test
    public void deleteCustomer() throws Exception {
        Long id=100L;
        boolean b = customerService.deleteCustomer(id);
        Assert.assertTrue(b);
    }

}