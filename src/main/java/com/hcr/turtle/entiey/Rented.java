package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 租房记录
 */
@Getter
@Setter
@ToString
public class Rented implements Serializable {
    private Integer id;
    private Integer renter;//租客编号
    private Integer houseid;//房屋编号
    private String begintime;//租期开始时间
    private String endtime;//租期结束时间
    private double hrend;//房屋全部租金
    private double charge;//手续费
    private String hadr;//房源地址
    private String cname;//租户姓名
}
