package com.zk.controller

import com.zk.service.CustomerService
import org.summer.framework.annotation.Action
import org.summer.framework.annotation.Controller
import org.summer.framework.annotation.Inject
import org.summer.framework.controller.Data
import org.summer.framework.controller.Param
import org.summer.framework.controller.View
import java.util.*

/**
 * Created by zk on 01/11/2017.
 */
@Controller
class CustomerController {

    @Inject
    internal var customerService: CustomerService? = null


    //    @Override
    //    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        List<Customer> customerList = customerService.getCustomerList();
    //        req.setAttribute("customerList",customerList);
    //        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);
    //    }

    @Action("post:/")
    fun post(param: Param): View {
        val customerList = customerService!!.customerList
        return View("customer.jsp").addModel("customerList", customerList)
    }

    @Action("get:/")
    fun index(param: Param): View {
        val customerList = customerService!!.customerList
        return View("customer.jsp").addModel("customerList", customerList)
    }

    @Action("get:/customer")
    fun getindex(param: Param): View {
        val customerList = customerService!!.customerList
        return View("customer.jsp").addModel("customerList", customerList)
    }

    @Action("get:/customer_json")
    fun getindex2(param: Param): Data {
        val customerList = customerService!!.customerList
        val alias = ArrayList<String>()
        alias.add("zk")
        alias.add("zk2")
        customerList[0].alias = alias
        return Data(customerList)
    }

    @Action("get:/customer_create")
    fun create(param: Param): View {
        return View("customer_edit.jsp")
    }

    @Action("post:/customer_create")
    fun createSubmit(param: Param): Data {
        val fields = param.map
        val customer = customerService!!.createCustomer(fields)
        return Data(customer)
    }

    @Action("get:/customer_edit")
    fun edit(param: Param): View {
        val id = param.getLong("id")
        val customer = customerService!!.getCustomer(id)
        return View("customer_edit.jsp").addModel("customer", customer)
    }

    @Action("put:/customer_edit")
    fun editSubmit(param: Param): Data {
        val id = param.getLong("id")
        val fields = param.map
        val customer = customerService!!.updateCustomer(id, fields)
        return Data(customer)
    }

    @Action("get:/customer_delete")
    fun delete(param: Param): View {
        val id = param.getLong("id")
        val aBoolean = customerService!!.deleteCustomer(id)

        val customerList = customerService!!.customerList
        return View("customer.jsp").addModel("customerList", customerList)
    }
}
