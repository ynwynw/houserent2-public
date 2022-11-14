package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.EmpState;
import com.hcr.turtle.entiey.Employee;
import com.hcr.turtle.entiey.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 员工操作
 */
public interface EmployeeMapper {

    /**登录
     * @param employee
     * @return
     */
    @Select("select * from employee where eusername=#{eusername} and epassword=#{epassword} " +
            "and state!=0")
    public Employee empLogin(Employee employee);

    /**经纪人获取所有员工
     * @param employee
     * @param rowBounds
     * @return
     */
    @Select("select e.*,s.sname sname,r.rname rname from employee e,role r,emp_state s " +
            "where e.roleid=r.id and e.state=s.id and e.roleid>1 and enumber like '%${enumber}%' ")
    public List<Employee> getAllEmp(Employee employee, RowBounds rowBounds);

    /**经纪人获取员工总条数
     * @param employee
     * @return
     */
    @Select("select count(1) from employee where roleid>2 and enumber like '%${enumber}%' ")
    public int empCount(Employee employee);

    /**添加员工
     * @param employee
     * @return
     */
    @Insert("insert into employee(enumber,ename,ecard,ephone,eusername,epassword,roleid,state) " +
            "values(#{enumber},#{ename},#{ecard},#{ephone},#{eusername},#{epassword},#{roleid},#{state})")
    public int addEmp(Employee employee);

    /**修改员工信息
     * @param employee
     * @return
     */
    @Update("update employee set enumber=#{enumber},ename=#{ename},ecard=#{ecard},ephone=#{ephone}, " +
            "roleid=#{roleid},state=#{state} where id=#{id} ")
    public int updateEmp(Employee employee);

    /**获取所有角色(下拉框)
     * @return
     */
    @Select("select id,rname from role where id>1")
    public List<Role> getRole();

    /**获取所有员工状态(下拉框)
     * @return
     */
    @Select("select * from emp_state")
    public List<EmpState> getEmpState();

    /**根据员工id查找对应信息
     * @param id
     * @return
     */
    @Select("select * from employee where id=#{id}")
    public List<Employee> getEmpByid(Integer id);

    /**根据员工id修改个人信息
     * @param employee
     * @return
     */
    @Update("update employee set eusername=#{eusername},eqq=#{eqq},email=#{email},edesc=#{edesc} where id=#{id}")
    public int upEmpnewsByid(Employee employee);


    /**根据id修改员工头像
     * @param employee
     * @return
     */
    @Update("update employee set logoimg=#{logoimg} where id=#{id}")
    public int upEmpimgByid(Employee employee);

    /**根据员工id修改密码
     * @param employee
     * @return
     */
    @Update("update employee set epassword=#{epassword} where id=#{id}")
    public int upEmpPswByid(Employee employee);


}
