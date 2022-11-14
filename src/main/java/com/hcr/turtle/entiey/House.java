package com.hcr.turtle.entiey;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class House implements Serializable {
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
    private String longitude;//经度
    private String latitude;//纬度
    private Integer agentid;  //经纪人id
    private Integer tenantid;  //租客ID
    private List<String> desctag;//房屋描述
    private List<String> himgs;//房屋详情图
    private List<String> installation;//配套设施
    private String cimg;//合同图片
    private String examine;//审核信息
    private Integer lesscount; //预约人数



}
