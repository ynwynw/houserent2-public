package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LeadHouse implements Serializable {
    private Integer id;//编号
    private String ladr;//地址
    private String remoney;//租金
    private String lname;//昵称
    private String lphone;//电话
    private Integer state;//状态
    private String lstate; //联查状态
}
