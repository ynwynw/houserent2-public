package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.*;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@SuppressWarnings("all")
@RequestMapping("house")
public class HouseController {
    @Autowired
    private HouseService houseService;

    /**
     * 获取房源列表
     */
    @RequestMapping("houseList/{current}/{pageSize}")
    public Page houseList(House house, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.houseList(house, current, pageSize);
    }

    /**
     * 后台数据表格获取全部房屋列表（所有房源）
     */
    @RequestMapping("houseBackList/{current}/{pageSize}")
    public Page houseBackList(House house, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.houseBackList(house, current, pageSize);
    }

    /**获取所有房屋状态
     * @return
     */
    @RequestMapping("/getAllHosState")
    public List<HouseState> getAllHosState(){
        return houseService.getAllHosState();
    }

    /**
     * 后台数据表格获取全部房屋联系
     */
    @RequestMapping("getAllLeadhouse/{current}/{pageSize}")
    public Page getAllLeadhouse(LeadHouse leadHouse, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.getAllLeadhouse(leadHouse, current, pageSize);
    }

    //根据ID修改leadhouse房源状态为已联系(1)
    @RequestMapping("updateLstate")
    public Result updateLstate(Integer id) {
        Integer i = houseService.updateLstate(id);
        if (i > 0) {
            return new Result(ISysConstants.SUCCESSCODE, "已联系该客户", null);
        } else {
            return new Result(ISysConstants.SUCCESSCODE, "操作失败", null);
        }
    }

    /**
     * 根据ID获取房屋详细信息
     */
    @RequestMapping("houseDetail")
    public Map<String, Object> houseDetail(Integer id) {
        return houseService.houseDetail(id);
    }

    /**
     *
     * 查询所有客户信息
     */
    @RequestMapping("cnumberAll")
    public List<Customer> cnumberAll() {
        return houseService.cnumberAll();
    }

    /**
     *
     * 根据房东编号cnumber查询房东id
     */
    @RequestMapping("cnumberById")
    public Integer cnumberById(Integer cnumber) {
        return houseService.cnumberById(cnumber);
    }

    /**
     *
     * 获取登录的经纪人信息
     */
    @RequestMapping("employeeSession")
    public Employee employeeSession() {
        return houseService.employeeSession();
    }

    /**
     *
     * 往house_furniture房屋设施中间表中添加数据
     */
    @RequestMapping(value = "housefurInsert", method = RequestMethod.POST)
    public Result housefurInsert(Integer houseid, @RequestParam("installation[]") String[] furniture) {
        return houseService.housefurInsert(houseid, furniture);
    }

    /**
     *
     * 往house_label房屋设施中间表中添加数据
     */
    @RequestMapping(value = "houselabelInsert", method = RequestMethod.POST)
    public Result houselabelInsert(Integer houseid, @RequestParam("hlable[]") String[] lables) {
        return houseService.houselabelInsert(houseid, lables);
    }

    /**
     *
     * 根据cnumber房东编号修改customer表中房东信息
     */
    @RequestMapping("updateCustomer")
    public Integer updateCustomer(Customer customer) {
        return houseService.updateCustomer(customer);
    }

    /**
     *
     * 根据房屋id查询房屋记录，验证房屋id是否重复
     */
    @RequestMapping("houseById")
    public Result houseById(Integer houseid){
        return houseService.houseById(houseid);
    }

    /**
     *
     * 往house表中插入房源信息
     */
    @RequestMapping("houseInsert")
    public Integer houseInsert(House house) {
        return houseService.houseInsert(house);
    }

    /**
     *
     * 往leadhouse表中添加发布房源信息
     */
    @RequestMapping("leadhouseInsert")
    public Result leadhouseInsert(LeadHouse leadHouse) {
        Integer integer = houseService.leadhouseInsert(leadHouse);
        if (integer > 0 || integer != null) {
            return new Result(ISysConstants.SUCCESSCODE, "提交成功", null);
        }
        return new Result(ISysConstants.ERRORCODE, "提交失败", null);
    }

    /**
     *
     * 往houseimgs表中插入房屋组图路径
     */
    @RequestMapping("houseimgsInsert")
    public Integer houseimgsInsert(HouseImgs houseImgs) {
        return houseService.houseimgsInsert(houseImgs);
    }

    /**
     * 根据ID删除房屋信息
     */
    @RequestMapping("remove")
    public Result remove(Integer id) {
        int i = houseService.delHouseById(id);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("删除成功");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("删除失败");
        }
        return result;
    }

    /**
     * 根据ID修改房源信息
     */
    @RequestMapping("update")
    public Result save(Houses houses) {
        //System.out.println(houses);
        int i = houseService.updateHouseInfo(houses);
        //System.out.println(i);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("修改成功");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("修改失败");
        }
        return result;
    }

    /**
     * 获取待审核房源
     */
    @RequestMapping("unauditedHouse/{current}/{pageSize}")
    public Page unauditedHouse(House house, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.unauditedHouse(house, current, pageSize);
    }

    /**
     * 更改待审核（2）的房源状态为已审核(4)
     */
    @RequestMapping("updateOne")
    public Result updateOne(Integer id) {
        //System.out.println(id);
        int i = houseService.updateStateOne(id);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("审核通过");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("审核出错");
        }
        return result;
    }

    /**
     * 更改待审核的房源状态为未通过(3)
     */
    @RequestMapping("updateTwo")
    public Result updateTwo(House house) {
        //System.out.println("iiiiiiii"+house);
        Integer i = houseService.updateStateTwo(house);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("已驳回");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("驳回失败");
        }
        return result;
    }

    /**
     * 更改已审核的房源状态为已发布
     */
    @RequestMapping("updateThree")
    public Result updateThree(Integer id) {
        //System.out.println(id);
        int i = houseService.updateStateThree(id);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("已发布");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("发布失败");
        }
        return result;
    }

    /**
     * 修改未通过的房源状态为待审核(2)
     */
    @RequestMapping("updateTwoHouse")
    public Result updateTwoHouse(@RequestBody House house) {
        //System.out.println(house);
        int i = houseService.updateTwoHouse(house);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("提交成功");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("提交失败");
        }
        return result;
    }

    /**
     * 修改已审核的房源状态为待发布(1)
     */
    @RequestMapping("updateFourHouse")
    public Result updateFourHouse(Integer id) {
        //System.out.println(id);
        int i = houseService.updateStateFour(id);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("下架成功");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("下架失败");
        }
        return result;
    }

    /**
     * 获取对应经纪人的房源
     */
    @RequestMapping("agentHouse/{current}/{pageSize}")
    public Page agentHouse(House house, @PathVariable int current, @PathVariable int pageSize) {
        return houseService.agentHouse(house, current, pageSize);
    }

    /**我的房源里面可以查看已出租的房的房客信息
     * @param id
     * @return
     */
    @RequestMapping("getAllRenCus")
    public List<RentedandCus> getAllRenCus(Integer id){
        return houseService.getAllRenCus(id);
    }

    /**
     * 关注房源
     */
    @RequestMapping("followHouse")
    public Result followHouse(Integer houseid) {
        int i = houseService.followhouse(houseid);
        if (i > 0) {
            return new Result(ISysConstants.SUCCESSCODE, "已关注", null);
        } else {
            return new Result(ISysConstants.SUCCESSCODE, "关注失败", null);
        }
    }

    /**
     * 判断是否关注
     */
    @RequestMapping("isFollow")
    public Result isFollow(Integer houseid) {
        FollowHouse follow = houseService.isFollow(houseid);
        if (follow != null) {
            return new Result(ISysConstants.SUCCESSCODE, "", follow);
        } else {
            return new Result(ISysConstants.ERRORCODE, "", follow);
        }
    }

    /**
     * 用户关注的房源
     */
    @RequestMapping("myFollowHouse")
    public Page myFollowHouse() {
        return houseService.myFollowHouse();
    }

    /**
     * 取消关注
     */
    @RequestMapping("unFollow")
    public Result unFollow(Integer houseid) {
        int i = houseService.delFollow(houseid);
        if (i > 0) {
            return new Result(ISysConstants.SUCCESSCODE, "", null);
        } else {
            return new Result(ISysConstants.ERRORCODE, "", null);
        }
    }

    /**
     * 用户发布的房源
     */
    @RequestMapping("myPostedHouse")
    public Page myPostedHouse() {
        return houseService.myPostedHouse();
    }



    /**
     * 根据房屋ID获取对应的配套设施
     */
    @RequestMapping("houseInstallation")
    public List<String> houseInstallation(Integer houseid) {
        return houseService.houseInstallation(houseid);
    }


    /**
     * 当前用户名下租赁的房子（乔 新增）
     * @return
     */
    @RequestMapping("/myRentHouse")
    public Page myRentHouse(){
        return houseService.myRentHouse();
    }

    /**
     * 查询报修填写的信息
     * @param id
     * @return
     */
    @RequestMapping("/myRepairs/{id}")
    public Object myRepairs(@PathVariable("id") Integer id){
        return houseService.myRepairs(id);
    }

}
