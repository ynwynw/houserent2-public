package com.hcr.turtle.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ChartMapper {
    /**
     * 算出每月租出的房屋总数
     * @return
     */
    @Select("SELECT COUNT(1) as num FROM rented WHERE optdate like '%${yearMonth}%'")
    public Integer rentOutNumber(@Param("yearMonth") String yearMonth);

    /**
     * 算出我的每月租出的房屋的总数
     * @return
     */
    @Select("SELECT COUNT(1) number\n" +
            "from house h,rented r\n" +
            "WHERE h.id=r.houseid and h.state=5 and h.agentid=#{angentid} and r.optdate like '%${yearMonth}%'")
    public Integer myRentOutNumber(@Param("yearMonth") String yearMonth, @Param("angentid") Integer angentid);


    /**
     * 算出制定年份中的月份的租金总额
     */
    @Select("<script>select "+
            "<if test='month!=null and month!=0'> date_format(r.begintime,'%m-%d') </if>  <if test='month==null or month==0'> date_format(r.begintime,'%Y-%m') </if> as months,(sum(hrend)) zh from rented r " +
            "where  date_format(r.begintime,'%Y')=#{year}" +
            "<if test='month!=null and month!=0'> and date_format(r.begintime,'%m')=#{month} </if>"+
            " group by <if test='month!=null and month!=0'> date_format(r.begintime,'%m-%d') </if>  <if test='month==null or month==0'> date_format(r.begintime,'%Y-%m')</if>" +
            " order by  <if test='month!=null and month!=0'> date_format(r.begintime,'%m-%d') </if>  <if test='month==null or month==0'> date_format(r.begintime,'%Y-%m')</if></script>")
    List<Map> rentedchart(Map map);



}
