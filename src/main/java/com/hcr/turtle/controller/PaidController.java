package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.PaidHouse;
import com.hcr.turtle.entiey.Rented;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.PaidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("all")
@RequestMapping("paid")
public class PaidController {
    @Autowired
    private PaidService paidService;


    /**
     * 查询所有已出租的房源的信息
     */
    @RequestMapping("paidList/{current}/{pageSize}")
    public Page houseBackList(Rented rented, @PathVariable int current, @PathVariable int pageSize) {
        return paidService.RentedList(rented, current, pageSize);
    }

    /**
     *
     * 在paid表中添加一条数据
     */
    @RequestMapping("paidHouse")
    public Result rentHouse(PaidHouse paidHouse) {
        int i = paidService.insertPaid(paidHouse);
        Result result = new Result();
        if (i > 0) {
            result.setCode(ISysConstants.SUCCESSCODE);
            result.setMsg("该房屋待缴租金");
        } else {
            result.setCode(ISysConstants.ERRORCODE);
            result.setMsg("操作异常");
        }
        return result;
    }


    /**
     * 查询所有待缴租金房源的信息
     */
    @RequestMapping("noPaidList/{current}/{pageSize}")
    public Page nopaidhouse(PaidHouse paidHouse, @PathVariable int current, @PathVariable int pageSize) {
        return paidService.NoPaidList(paidHouse, current, pageSize);
    }


    /**
     * 查询所有已缴租金房源的信息
     */
    @RequestMapping("yetPaidList/{current}/{pageSize}")
    public Page yetpaidhouse(PaidHouse paidHouse, @PathVariable int current, @PathVariable int pageSize) {
        return paidService.YetPaidList(paidHouse, current, pageSize);
    }

    /**
     * 查询登录租户所有待缴租金房源的信息
     */
    @RequestMapping("staypaid/{current}/{pageSize}")
    public Page staypaid(PaidHouse paidHouse, @PathVariable int current, @PathVariable int pageSize) {
//        System.out.println(paidService.staypaid(paidHouse,current,pageSize));
        return paidService.staypaid(paidHouse, current, pageSize);
    }


    /**
     * 查询所有已缴租金房源的信息,限定登录的租户
     */
    @RequestMapping("havepaid/{current}/{pageSize}")
    public Page havepaid(PaidHouse paidHouse, @PathVariable int current, @PathVariable int pageSize) {
        return paidService.havepaid(paidHouse, current, pageSize);
    }
    /*
     * 更改为交租状态为2
     * */

    @RequestMapping("update")
    public String upLessState(Integer id) {
        //System.out.println(id+"....................");
        int result = paidService.upLessState(id);
        //System.out.println(result);
        return result==0?"false":"true";

    }



}
