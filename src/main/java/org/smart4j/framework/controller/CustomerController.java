package org.smart4j.framework.controller;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.model.Customer;
import org.smart4j.framework.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by zk on 01/11/2017.
 */
@Controller
public class CustomerController {

    @Inject
    CustomerService customerService;


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Customer> customerList = customerService.getCustomerList();
//        req.setAttribute("customerList",customerList);
//        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
//    }
@Action("get:/")
public View index(Param param){
    List<Customer> customerList = customerService.getCustomerList();
    return new View("customer.jsp").addModel("customerList",customerList);
}

    @Action("get:/customer")
    public View getindex(Param param){
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList",customerList);
    }
    @Action("get:/customer_json")
    public Data getindex2(Param param){
        List<Customer> customerList = customerService.getCustomerList();
        return new Data(customerList);
    }

    @Action("get:/customer_create")
    public View create(Param param){
        return new  View("customer_create.jsp");
    }

    @Action("post:/customer_create")
    public Data createSubmit(Param param){
        Map<String,Object> fields=param.getMap();
        boolean customer = customerService.createCustomer(fields);
        return new Data(customer);
    }

    @Action("get:/customer_edit")
    public View edit(Param param){
        Long id =param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new  View("customer_edit.jsp").addModel("customer",customer);
    }

    @Action("put:/customer_edit")
    public Data editSubmit(Param param){
        Long id =param.getLong("id");
        Map<String,Object> fields=param.getMap();
        boolean customer = customerService.updateCustomer(id,fields);
        return new Data(customer);
    }

    @Action("get:/customer_delete")
    public View delete(Param param){
        Long id =param.getLong("id");
        Boolean aBoolean = customerService.deleteCustomer(id);

        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList",customerList);
    }
}
