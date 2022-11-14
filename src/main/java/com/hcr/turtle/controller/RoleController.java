package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.Role;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**获取所有角色
     * @param role
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getRole/{currentPage}/{pageSize}")
    public Page getAllRole(Role role, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return roleService.getAllRole(role, currentPage, pageSize);
    }

    /**获取所有角色编号
     * @param role
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getRoleByNum")
    public Result getRoleByNum(Integer rnum){
        Role roleByNum = roleService.getRoleByNum(rnum);
        Result result=new Result();
        if (roleByNum!=null){
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setObject(roleByNum);
        }else{
            result.setCode(ISysConstants.ERRORCODE);
        }
        return result;
    }

    /**添加修改角色信息
     * @param role
     * @return
     */
    @RequestMapping("/saveRole")
    public Result addRole(Role role){
        Result result=new Result();
        if (role.getId()==null || role.getId()==0){
            int addRole = roleService.addRole(role);
            if (addRole>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("添加成功");
            }else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("添加失败");
            }
        }else {
            int updateRole = roleService.updateRole(role);
            if (updateRole>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("修改成功");
            }else{
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("修改失败");
            }
        }
        return result;
    }

    /**删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole/{id}")
    public Result deleteRole(@PathVariable Integer id){
        int deleteRole = roleService.deleteRole(id);
        Result result=new Result();
        if (deleteRole>0){
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("删除成功");
        }else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("删除失败");
        }
        return result;
    }


    /**获取所有角色编号
     * @return
     */
    @RequestMapping("/getRoleNum")
    public int[] getRoleNum(){
        int[] role = roleService.getRoleNum();
        if (role==null){
            return null;
        }
        return role;
    }


}
