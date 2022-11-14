package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.CusState;
import com.hcr.turtle.entiey.Customer;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 获取所有客户
     *
     * @param role
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllCus/{currentPage}/{pageSize}")
    public Page getAllCus(Customer customer, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize) {
        return customerService.getAllCus(customer, currentPage, pageSize);
    }

    /**
     * 添加修改客户信息
     *
     * @param role
     * @return
     */
    @RequestMapping("/saveCus")
    public Result saveEmp(Customer customer) {
        Result result = new Result();
        if (customer.getId() == null || customer.getId() == 0) {
            int addCus = customerService.addCus(customer);
            if (addCus > 0) {
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("添加成功");
            } else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("添加失败");
            }
        } else {
            int updateCus = customerService.updateCus(customer);
            if (updateCus > 0) {
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("修改成功");
            } else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("修改失败");
            }
        }
        return result;
    }

    /**
     * 获取客户状态
     * @return
     */
    @RequestMapping("/getCusState")
    public Result getCusState() {
        List<CusState> cusState = customerService.getCusState();
        if (cusState != null) {
            return new Result(ISysConstants.SUCCESSCODE, null, cusState);
        }
        return new Result(ISysConstants.ERRORCODE, null, null);
    }

    /**
     * 获取所有提供房源的客户(下拉框)
     */
    @RequestMapping("/getCus")
    public List<Customer> getCus() {
        return customerService.getAll();
    }

    /**
     * 根据ID获取提供房源的客户信息
     */
    @RequestMapping("/getCusInfo")
    public Customer getCusInfo(Integer id) {
        return customerService.getCusInfo(id);
    }

    /**
     * 从Session中取出用户信息
     */
    @RequestMapping("/getCusFromSession")
    public Result getCusFromSession(){
        Customer customer = customerService.getCusFromSession();
        if (customer !=null){
            return new Result(ISysConstants.SUCCESSCODE,"存在",customer);
        } else {
            return new Result(ISysConstants.ERRORCODE,"请重新登录",null);
        }
    }

}
