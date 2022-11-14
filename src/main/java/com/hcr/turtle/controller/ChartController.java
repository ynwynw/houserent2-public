package com.hcr.turtle.controller;

import com.hcr.turtle.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@SuppressWarnings("all")
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private ChartService chartService;
    /**xu
     * 往leadhouse表中添加发布房源信息
     */
    @RequestMapping("rentOutNumber")
    public List rentOutNumber(String year){
        return chartService.rentOutNumber(year);
    }


    @RequestMapping("rentedchart")
    public List<Map> rentedchart(@RequestParam Map map){
        return chartService.rentedchart(map);
    }


}
