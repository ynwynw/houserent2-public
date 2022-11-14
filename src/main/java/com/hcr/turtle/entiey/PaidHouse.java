package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/*
 *fileName:PaidHouse
 *description:
 *author:周铎
 *createTime:2019-07-30 12:26
 *version：1.0.0
 */
//收租详情实体类
@Getter
@Setter
@ToString
public class PaidHouse {

    private Integer id;
    private Integer houseid;
    private Integer tenantid;
    private String tenant;
    private String hadr;
    private Integer hrend;
    private String data;
    private String name;


}
