package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.EmpState;
import com.hcr.turtle.entiey.Employee;
import com.hcr.turtle.entiey.Role;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**获取所有员工
     * @param role
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllEmp/{currentPage}/{pageSize}")
    public Page getAllEmp(Employee employee, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return employeeService.getAllEmp(employee, currentPage, pageSize);
    }

    /**添加修改员工信息
     * @param role
     * @return
     */
    @RequestMapping("/saveEmp")
    public Result saveEmp(Employee employee){
        Result result=new Result();
        if (employee.getId()==null || employee.getId()==0){
            int addEmp = employeeService.addEmp(employee);
            if (addEmp>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("添加成功");
            }else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("添加失败");
            }
        }else {
            int updateEmp = employeeService.updateEmp(employee);
            if (updateEmp>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("修改成功");
            }else{
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("修改失败");
            }
        }
        return result;
    }

    /**获取角色(下拉框)
     * @return
     */
    @RequestMapping("/getRole")
    public Result getRole(){
        List<Role> role = employeeService.getRole();
        if (role!=null){
            return new Result(ISysConstants.SUCCESSCODE,null,role);
        }
        return new Result(ISysConstants.ERRORCODE,null,null);
    }

    /**获取所有员工状态(下拉框)
     * @return
     */
    @RequestMapping("/getEmpState")
    public Result getEmpState(){
        List<EmpState> empState = employeeService.getEmpState();
        if (empState!=null){
            return new Result(ISysConstants.SUCCESSCODE,null,empState);
        }
        return new Result(ISysConstants.ERRORCODE,null,null);
    }

    /**根据id查找对应员工信息
     * @return
     */
    @RequestMapping("/getEmpByid")
    public Result getEmpByid(){
        List<Employee> emp = employeeService.getEmpByid();
        if(emp!=null){
            return new Result(ISysConstants.SUCCESSCODE,null,emp.get(0));
        }
        return new Result(ISysConstants.ERRORCODE,null,null);
    }

    /**根据员工id修改个人信息
     * @param employee
     * @return
     */
    @RequestMapping("/upEmpnewsByid")
    public Result upEmpnewsByid(Employee employee){
        int empnewsByid = employeeService.upEmpnewsByid(employee);
        if (empnewsByid>0){
            return new Result(ISysConstants.SUCCESSCODE,"修改成功",null);
        }
        return new Result(ISysConstants.ERRORCODE,"修改失败",null);
    }

    /**根据员工id修改密码
     * @param employee
     * @return
     */
    @RequestMapping("/upEmpPswByid")
    public Result upEmpPswByid(Employee employee){
        int pswByid = employeeService.upEmpPswByid(employee);
        if (pswByid>0){
            return new Result(ISysConstants.SUCCESSCODE,"修改成功,请重新登录~",null);
        }
        return new Result(ISysConstants.ERRORCODE,"修改失败",null);
    }


}
