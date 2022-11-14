package com.hcr.turtle.controller;

import com.hcr.turtle.entiey.Wrong;
import com.hcr.turtle.util.ISysConstants;
import com.hcr.turtle.util.Page;
import com.hcr.turtle.util.Result;
import com.hcr.turtle.service.WrongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * fileName:WrongController
 * author:Qiao
 * createTime:2019/7/31
 * version:1.0.0
 */
@RestController
@RequestMapping("/wrong")
public class WrongController {
    @Autowired
    private WrongService service;

    @RequestMapping("/wrongList/{current}/{pageSize}")
    public Page wrongList(Wrong wrong, @PathVariable int current, @PathVariable int pageSize){
        return service.wrongList(wrong,current,pageSize);
    }
    @RequestMapping("/updateWrong")
    public Result updateWrong(Integer id){
        Integer i = service.updateWrong(id);
        if (i > 0) {
            return new Result(ISysConstants.SUCCESSCODE, "房屋已经维修", null);
        } else {
            return new Result(ISysConstants.ERRORCODE, "抱歉暂时未给你处理", null);
        }
    }

    @RequestMapping("/wrong2List/{current}/{pageSize}")
    public Page wrong2List(Wrong wrong, @PathVariable int current, @PathVariable int pageSize){
        return service.wrong2List(wrong,current,pageSize);
    }

    @RequestMapping("/saveWrong")
    public Result saveWrong(@RequestBody Map map){
        Integer i = service.saveWrong(map);
        if (i > 0) {
            return new Result(ISysConstants.SUCCESSCODE, "维修信息已传递", null);
        } else {
            return new Result(ISysConstants.ERRORCODE, "抱歉暂时你的消息没有送达", null);
        }
    }
}
