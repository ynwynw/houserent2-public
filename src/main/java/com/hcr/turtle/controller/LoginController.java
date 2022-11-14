package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.Customer;
import com.hcr.turtle.entiey.CustomerAPP;
import com.hcr.turtle.entiey.Employee;
import com.hcr.turtle.util.CusUtil;
import com.hcr.turtle.util.EmpUtil;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService service;

/*=====================================客户登录部分(开始)=====================================*/

    /**判断是否已经登录
     * @return
     */
    @RequestMapping("/judgeCusLogin")
    public Result judgeCusLogin(){
        Customer judgeCusLogin = service.judgeCusLogin();
        Result result=new Result();
        if (judgeCusLogin==null){
            result.setCode(ISysConstants.ERRORCODE);
        }else{
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setObject(judgeCusLogin.getCusername());
        }
        return result;
    }

    /**客户登录
     * @param customer
     * @return
     */
    @RequestMapping("/cusLogin")
    public Result cusLogin(Customer customer){
        Customer login = service.cusLogin(customer);
        Result result=new Result();
        if (login!=null){
            result.setCode(ISysConstants.SUCCESSCODE);
        }else{
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("登录失败,手机号或密码错误");
        }
        return result;
    }

    /**检测手机号获取验证码
     * @param customer
     * @return
     */
    @RequestMapping("/getUsableGetPhone")
    public Result getUsableGetPhone(Customer customer){
        Customer customer1 = service.getUsableGetPhone(customer);
        Result result=new Result();
        if (customer1!=null){
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("该手机号已经注册");
        }else{
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("验证码已发送，请在5分钟内使用");
        }
        return result;
    }

    /**检测手机号获取验证码(手机端)
     * @param customer
     * @return
     */
    @RequestMapping("/getUsableGetPhoneApp")
    public Result getUsableGetPhoneApp(@RequestBody Customer customer){
        Customer customer1 = service.getUsableGetPhone(customer);
        Result result=new Result();
        if (customer1!=null){
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("该手机号已经注册");
        }else{
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("验证码已发送，请在5分钟内使用");
        }
        return result;
    }

    /**修改密码时检测手机号获取验证码(手机端)
     * @param customer
     * @return
     */
    @RequestMapping("/getCodeApp")
    public Result getCodeApp(@RequestBody Customer customer){
        Customer customer2 = service.getUsableGetPhone(customer);
        Result result=new Result();
        if (customer2==null){
            result.setCode(ISysConstants.ERRORCODE);
            //result.setMsg("该手机号未注册");
        }else{
            result.setCode(ISysConstants.SUCCESSCODE);
            //result.setMsg("验证码已发送，请在5分钟内使用");
        }
        return result;
    }




    /**
     * 销毁session退出登录
     */
    @RequestMapping("/killCusSession")
    public Result killCusSession(){
        CusUtil.removeCusson();
        return new Result(ISysConstants.SUCCESSCODE,null,null);
    }


    /**用户注册
     * @param customer
     * @param code
     * @return
     */
    @RequestMapping("/registerCus")
    public Result registerCus(Customer customer,String code){
        int reg=service.registerCus(customer,code);
        Result result=new Result();
        if (reg==-1){
            result.setCode(ISysConstants.OTHERTIPS);
            result.setMsg("验证码错误");
        }else if(reg==1){
            result.setCode(ISysConstants.SUCCESSCODE);
        }else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("注册失败");
        }
        return result;
    }

    /**用户注册APP
     * @param customer
     * @param code
     * @return
     */
    @RequestMapping("/registerCusAPP")
    public Result registerCusAPP(@RequestBody CustomerAPP customerAPP){
        System.out.println(customerAPP);
        int reg=service.registerCusAPP(customerAPP,customerAPP.getCode());
        Result result=new Result();
        if (reg==-1){
            result.setCode(ISysConstants.OTHERTIPS);
            result.setMsg("验证码错误");
        }else if(reg==1){
            result.setCode(ISysConstants.SUCCESSCODE);
        }else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("注册失败");
        }
        return result;
    }


/*=====================================客户登录部分(结束)=====================================*/

/*=====================================员工登录部分(开始)=====================================*/

    /**员工登录
     * @param employee
     * @return
     */
    @RequestMapping("/empLogin")
    public Result empLogin(Employee employee){
        Employee empLogin = service.empLogin(employee);
        Result result=new Result();
        if (empLogin!=null){
            result.setCode(ISysConstants.SUCCESSCODE);
        }else{
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("登录失败,用户名密码错误");
        }
        return result;
    }

    /**判断是否登录
     * @return
     */
    @RequestMapping("/judgeEmpLogin")
    public Result judgeEmpLogin(){
        Employee judgeEmpLogin = service.judgeEmpLogin();
        Result result=new Result();
        if (judgeEmpLogin!=null){
            Map map=new HashMap();
            map.put("username",judgeEmpLogin.getEname());
            map.put("logoimg",judgeEmpLogin.getLogoimg());
            map.put("roleid",judgeEmpLogin.getRoleid());
            map.put("edesc",judgeEmpLogin.getEdesc());
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setObject(map);
        }else{
            result.setCode(ISysConstants.ERRORCODE);
        }
        return result;
    }

    /**
     * 销毁session退出登录
     */
    @RequestMapping("/killEmpSession")
    public Result killEmpSession(){
        EmpUtil.removeEmployee();
        return new Result(ISysConstants.SUCCESSCODE,null,null);
    }


/*=====================================员工登录部分(开始)=====================================*/

}
