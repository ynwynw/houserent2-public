package com.hcr.turtle.entiey;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * fileName:Wrong
 * author:Qiao
 * createTime:2019/7/31
 * version:1.0.0
 */
@Getter
@Setter
@ToString
/**
 * 新增wrong实体类（乔 新增）
 */
public class Wrong implements Serializable {
    private int id;
    private int houseId;//房屋编号houseid
    private int tenantId;//租客id
    private String tenant;//租客姓名
    private String hadr;//房屋地址
    private String data;//报障时间
    private String detail;//报障详情
    private  int state;//处理状态
    private String optdata;//处理故障的时间
    private String name; //处理状态的名字
}
