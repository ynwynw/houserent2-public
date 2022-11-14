package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Houses implements Serializable {
    private Integer id;
    private Integer houseid;//房屋编号
    private String landlord;//房东编号
    private String htitle;//房屋标题
    private String hadr;//房屋地址
    private Integer hrent;//房屋租金
    private Integer state;//房屋状态
    private String hstate; //状态名字
    private String himg;//房屋图片
    private Integer hfloor;//房屋楼层
    private String hspace;//房屋面积
    private String orientation;//房屋朝向
    private String releasedate;//房屋发布日期
    private String newstime;//发布距今天数
    private String hdesc;// 房屋格局
}
