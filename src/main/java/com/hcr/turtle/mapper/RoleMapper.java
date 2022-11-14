package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 角色操作
 */
public interface RoleMapper {

    /**获取所有角色
     * @return
     */
    @Select("select * from role where rnum like '%${rnum}%' ")
    public List<Role> getAllRole(Role role, RowBounds rowBounds);

    /**根据编号获取角色
     * @return
     */
    @Select("select * from role where rnum=#{rnum} ")
    public Role getRoleByNum(Integer rnum);

    /**获取总条数
     * @param role
     * @return
     */
    @Select("select count(1) from role where rnum like '%${rnum}%'")
    public int roleCount(Role role);

    /**添加角色
     * @param role
     * @return
     */
    @Insert("insert into role (rnum,rname,rdesc) values (#{rnum},#{rname},#{rdesc})")
    public int addRole(Role role);


    /**修改角色信息
     * @param role
     * @return
     */
    @Update("update role set rnum=#{rnum},rname=#{rname},rdesc=#{rdesc} where id=#{id}")
    public int updateRole(Role role);

    /**删除角色
     * @param
     * @return
     */
    @Delete("delete from role where id=#{id}")
    public int deleteRole(Integer id);

    /**获取所有角色编号
     * @return
     */
    @Select("select rnum from role")
    public int[] getRoleNum();


}
