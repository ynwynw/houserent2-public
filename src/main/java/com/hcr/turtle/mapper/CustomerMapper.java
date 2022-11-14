package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.CusState;
import com.hcr.turtle.entiey.Customer;
import com.hcr.turtle.entiey.CustomerAPP;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 客户操作
 */
public interface CustomerMapper {

    /**登录
     * @param customer
     * @return
     */
    @Select("select * from customer where cphone=#{cphone} and cpassword=#{cpassword} and state=1")
    public Customer cusLogin(Customer customer);

    /**检测手机号获取验证码
     * @param customer
     * @return
     */
    @Select("select * from customer where cphone=#{cphone}")
    public Customer getUsableGetPhone(Customer customer);


    /**用户注册
     * @param customer
     * @return
     */
    @Insert("insert into customer (cnumber,cphone,cusername,cpassword,state) values (#{cnumber},#{cphone},#{cusername},#{cpassword},1)")
    public int registerCus(Customer customer);

    /**用户注册APP
     * @param customerAPP
     * @return
     */
    @Insert("insert into customer (cnumber,cphone,cusername,cpassword,state) values (#{cnumber},#{cphone},#{cusername},#{cpassword},1)")
    public int registerCusAPP(CustomerAPP customerAPP);

    /**获取所有用户
     * @param customer
     * @param rowBounds
     * @return
     */
    @Select("select c.*,s.csname csname from customer c,cus_state s where c.state=s.id and cnumber like '%${cnumber}%'")
    public List<Customer> getAllCus(Customer customer, RowBounds rowBounds);


    /**获取总条数
     * @param customer
     * @return
     */
    @Select("select count(1) from customer where cnumber like '%${cnumber}%' ")
    public int cusCount(Customer customer);


    /**添加用户
     * @param customer
     * @return
     */
    @Insert("insert into customer(cnumber,cname,csex,cphone,ccard,cusername,cpassword,state) " +
            "values(#{cnumber},#{cname},#{csex},#{cphone},#{ccard},#{cusername},#{cpassword},#{state}) ")
    public int addCus(Customer customer);

    /**修改用户信息
     * @param customer
     * @return
     */
    @Update("update customer set cname=#{cname},csex=#{csex},cphone=#{cphone},ccard=#{ccard},state=#{state},cpassword=#{cpassword} where id=#{id}")
    public int updateCus(Customer customer);

    /**获取所有客户状态(下拉框)
     * @return
     */
    @Select("select * from cus_state")
    public List<CusState> getCusState();

    /**
     * 获取所有用户(下拉框)
     */
    @Select("select * from customer where state=1")
    public List<Customer> getAll();

    /**
     * 根据ID获取用户信息
     */
    @Select("select * from customer where id=#{id}")
    public Customer getCusInfo(Integer id);

    /**
     * 个人中心
     * 修改用户信息
     */
    @Update("update customer set cname=#{cname},csex=#{csex},cphone=#{cphone},ccard=#{ccard},state=#{state} where id=#{id}")
    public int updateCusInfo(Customer customer);

    /**
     * 个人中心
     * 修改用户头像
     */
    @Update("update customer set cimg=#{cimg} where id=#{id}")
    public int updateCusImg(Customer customer);
}
