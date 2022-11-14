package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.Permission;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**根据用户id获取一二级菜单
     * @return
     */
    @RequestMapping("/getMenuTree")
    public Result getMenuTree(){
        Object object=menuService.getMenuTree();
        Result result=new Result();
        result.setCode(ISysConstants.SUCCESSCODE);
        result.setObject(object);
        return result;
    }

    /**获取所有父菜单
     * @param permission
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getFatherMenu/{currentPage}/{pageSize}")
    public Page getFatherMenu(Permission permission, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return menuService.getFatherMenu(permission, currentPage, pageSize);
    }

    /**添加修改父菜单信息
     * @param
     * @return
     */
    @RequestMapping("/saveFatherMenu")
    public Result saveFatherMenu(Permission permission){
        Result result=new Result();
        if (permission.getId()==null || permission.getId()==0){
            int addFatherMenu = menuService.addFatherMenu(permission);
            if (addFatherMenu>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("添加成功");
            }else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("添加失败");
            }
        }else {
            int updateFatherMenu = menuService.updateFatherMenu(permission);
            if (updateFatherMenu>0){
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
    @RequestMapping("/deleteFatherMenu/{id}")
    public Result deleteFatherMenu(@PathVariable Integer id){
        int deleteFatherMenu = menuService.deleteFatherMenu(id);
        Result result=new Result();
        if (deleteFatherMenu>0){
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("删除成功");
        }else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**获取所有子菜单
     * @param permission
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getSonMenu/{currentPage}/{pageSize}")
    public Page getSonMenu(Permission permission, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return menuService.getSonMenu(permission, currentPage, pageSize);
    }

    /**添加修改子菜单信息
     * @param
     * @return
     */
    @RequestMapping("/saveSonMenu")
    public Result saveSonMenu(Permission permission){
        Result result=new Result();
        if (permission.getId()==null || permission.getId()==0){
            int addSonMenu = menuService.addSonMenu(permission);
            if (addSonMenu>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("添加成功");
            }else {
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("添加失败");
            }
        }else {
            int updateSonMenu = menuService.updateSonMenu(permission);
            if (updateSonMenu>0){
                result.setCode(ISysConstants.SUCCESSCODE);
                result.setMsg("修改成功");
            }else{
                result.setCode(ISysConstants.ERRORCODE);
                result.setMsg("修改失败");
            }
        }
        return result;
    }

    /**删除子菜单
     * @param id
     * @return
     */
    @RequestMapping("/deleteSonMenu/{id}")
    public Result deleteSonMenu(@PathVariable Integer id){
        int deleteSonMenu = menuService.deleteSonMenu(id);
        Result result=new Result();
        if (deleteSonMenu>0){
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("删除成功");
        }else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("删除失败");
        }
        return result;
    }

}
