package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.PaidHouse;
import com.hcr.turtle.entiey.Rented;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PaidMapper {

    /**获取所有已出租房源信息，从rented中获取
     * @return
     */
    @Select("SELECT r.houseid,r.renter,c.cname,h.hadr,r.hrend,r.begintime,r.endtime,r.charge\n" +
            "FROM rented r,house h,customer c\n" +
            "WHERE r.houseid=h.id AND r.renter=c.id AND hadr like '%${hadr}%'\t")
    public List<Rented> getAllRented(Rented rented, RowBounds rowBounds);


    /**
     * 获取房源列表总数
     */
    @Select("select count(1) from rented ")
    Integer BackListcount(Rented rented);


    /**
     * 往paid表录入待缴租的客户信息
     */
    @Insert("INSERT INTO paid (houseid,tenantid,tenant,hadr,hrend,data) VALUES (#{houseid},#{tenantid},#{tenant},#{hadr},#{hrend},#{data})")
    int insertPaid(PaidHouse paidHouse);


    /**
     * 查询出paid表中待缴租金的用户
     */
    @Select("SELECT p.houseid,p.tenant,p.hadr,p.hrend,p.data,ps.name\n" +
            "FROM paid p,paid_state ps\n" +
            "WHERE p.state=ps.id AND state=1 AND hadr like '%${hadr}%'")
    public List<PaidHouse> getNoPaid(PaidHouse paidHouse, RowBounds rowBounds);
    /**
     * 获取待缴租金房源总数
     */
    @Select("select count(1) from paid where state=1 ")
    Integer BackListpaid(PaidHouse paidHouse);




    /**
     * 查询出paid表中已缴租金的用户
     */
    @Select("SELECT p.houseid,p.tenant,p.hadr,p.hrend,p.data,ps.name\n" +
            "FROM paid p,paid_state ps\n" +
            "WHERE p.state=ps.id AND state=2 AND hadr like '%${hadr}%'")
    public List<PaidHouse> getYetPaid(PaidHouse paidHouse, RowBounds rowBounds);
    /**
     * 获取待缴租金房源总数
     */
    @Select("select count(1) from paid where state=2 ")
    Integer BackListYetpaid(PaidHouse paidHouse);


    /**
     * 获取房屋对应的描述标签
     */
    @Select("select l.labelcontent labelcontent from house h left join house_label hl " +
            "on h.houseid=hl.houseid join label l on hl.labelid=l.labelid " +
            "where h.houseid=#{houseid}")
    List<String> lableList(Integer houseid);

    /**
     * 根据房屋ID获取对应的配套设施
     */
    @Select("select f.furniturename from house h,house_furniture hf,furniture f " +
            "where h.houseid=hf.houseid and hf.furnitureid=f.furnitureid " +
            "and h.houseid=#{houseid}")
    List<String> houseInstallation(Integer houseid);

    /**
     * 根据租户ID获取他租的全部房源的数量
     */
    @Select("select count(f.id) from house h, rented f where h.id=f.houseid and f.renter=#{cid}")
    int myFollowHouseCount(Integer cid);

    /**
     * 查询出paid表中待缴租金
     */
    @Select("SELECT p.houseid,p.tenant,p.hadr,p.hrend,p.data,ps.name\n" +
            "                   FROM paid p,paid_state ps\n" +
            "                 WHERE p.state=ps.id AND state=1 AND tenantid=#{tenantid}")
    public List<PaidHouse> staypaid(PaidHouse paidHouse, RowBounds rowBounds,Integer tenantid);
    /**
     * 获取待缴租金房源总数
     */
    @Select("select count(1) from paid where state=1  AND tenantid=#{tenantid}")
    Integer staynupaid(PaidHouse paidHouse,Integer tenantid);


    /**
     * 查询出paid表中已缴租金
     */
    @Select("SELECT p.houseid,p.tenant,p.hadr,p.hrend,p.data,ps.name\n" +
            "FROM paid p,paid_state ps\n" +
            "WHERE p.state=ps.id AND state=2 AND tenantid=#{tenantid}")
    public List<PaidHouse> havepaid(PaidHouse paidHouse, RowBounds rowBounds,Integer tenantid);
    /**
     * 获取已缴租金房源总数
     */
    @Select("select count(1) from paid where state=2 AND tenantid=#{tenantid}")
    Integer havenupaid(PaidHouse paidHouse,Integer tenantid);
    /**
     * 更改为交租状态为2
     */
    @Update("update paid set state=2 where houseid=#{id}")
    int updaterent( Integer id);




}
