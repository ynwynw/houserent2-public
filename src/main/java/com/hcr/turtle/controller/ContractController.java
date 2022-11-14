package com.hcr.turtle.controller;

import com.hcr.turtle.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ContractController
 * @Author Libbly
 * @Date 2019/7/26 21:27
 */
@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    ContractService contractService;

    /**
     * 根据房屋ID查询合同
     *
     * @param hid
     * @return
     */
    @RequestMapping("/getContract")
    private Object requestJson(Integer hid) {
        List<Map<String, Object>> list = contractService.getContract(hid);
        if (list != null && list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
