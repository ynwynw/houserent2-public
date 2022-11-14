package com.hcr.turtle.controller;

import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**根据角色编号获取权限
     * @return
     */
    @RequestMapping("/getRolePer")
    public Result getRolePer(Integer rnum){
        Object object=permissionService.getRolePer(rnum);
        Result result=new Result();
        result.setCode(ISysConstants.SUCCESSCODE);
        result.setObject(object);
        return result;
    }

    Integer rid; //rid

    /**根据rid获取对应的pid
     * @param id
     * @return
     */
    @RequestMapping("/getPidByRid/{id}")
    public Result getPidByRid(@PathVariable Integer id){
        List list = permissionService.getPidByRid(id);
        rid=id;
        Result result=new Result();
        if (list!=null){
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setObject(list);
        }else{
            result.setCode(ISysConstants.ERRORCODE);
        }
        return result;
    }

    /**修改权限
     * @param pid
     * @return
     */
    @RequestMapping("/setPerOrRole")
    public Result setPerOrRole(@RequestParam(value = "pid[]",required=false) int[] pid){
        int a = permissionService.deletePer(rid);
        Map map = new HashMap();
        map.put("rid", rid);
        map.put("pid", pid);
        int b=permissionService.addPer(map);
        return new Result(ISysConstants.SUCCESSCODE,"修改成功!",null);
    }

}
