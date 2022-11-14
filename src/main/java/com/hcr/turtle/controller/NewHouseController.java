package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.House;
import com.hcr.turtle.entiey.Rented;
import com.hcr.turtle.entiey.Seehistory;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SuppressWarnings("all")
@RequestMapping("/newhouse")
public class NewHouseController {

    @Autowired
    private NewHouseService houseService;

    /**预约看房
     * @param id
     * @return
     */
    @RequestMapping("/addHosofCus")
    public Result addHosofCus(Integer id){
        int hosofCus = houseService.addHosofCus(id);
        if (hosofCus>0){
            return new Result(ISysConstants.SUCCESSCODE,"预约成功,我稍后会与您联系~",null);
        }
        return new Result(ISysConstants.ERRORCODE,"预约失败",null);
    }

    /**
     * 获取对应经纪人的房源(已发布房源)
     */
    @RequestMapping("getEmpHos/{current}/{pageSize}")
    public Page getEmpHos(House house, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.getEmpHos(house, current, pageSize);
    }

    /**根据房源id获取对应预约人资料
     * v根据房源id获取对应预约人资料，联合客户表，预约看房的客户表和房源表（house,seehistory,customer）
     * @param id
     * @return
     */
    @RequestMapping("getLessByHosId")
    public List<Seehistory> getLessByHosId(Integer id) {
        List<Seehistory> lessByHosId = houseService.getLessByHosId(id);
        //System.out.println(lessByHosId);
        if (lessByHosId!=null){
            return houseService.getLessByHosId(id);
        }
        return null;
    }


    /**修改客户预约信息(已看房)
     * @param id
     * @return
     */
    @RequestMapping("upLessState")
    public Result upLessState(Integer id) {
        int i = houseService.upLessState(id);
        if (i>0){
            return new Result(ISysConstants.SUCCESSCODE,"操作成功",null);
        }
        return new Result(ISysConstants.ERRORCODE,"操作失败",null);
    }

    /**根据id修改看房记录
     * @param seehistory
     * @return
     */
    @RequestMapping("upCusAndSee")
    public Result upCusAndSee(Seehistory seehistory){
        int i = houseService.upCusAndSee(seehistory);
        if (i>0){
            return new Result(ISysConstants.SUCCESSCODE,"修改成功",null);
        }
        return new Result(ISysConstants.ERRORCODE,"修改失败",null);
    }

    /**
     * 租房
     * 在rented表中添加一条数据
     */
    @RequestMapping("rentHouse")
    public Result rentHouse(Rented rented) {
        int i = houseService.rentHouse(rented);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("该房屋被成功租出");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("操作异常");
        }
        return result;
    }

    /**根据用户id删除看房记录
     * @param id
     * @return
     */
    @RequestMapping("deleSeeByCusId")
    public Result deleSeeByCusId(Integer id) {
        int i = houseService.deleSeeByCusId(id);
        if (i>0){
            return new Result(ISysConstants.SUCCESSCODE,"删除成功",null);
        }
        return new Result(ISysConstants.ERRORCODE,"操作失败",null);
    }


}
