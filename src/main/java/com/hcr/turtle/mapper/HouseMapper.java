package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface HouseMapper {
    /**
     * 获取全部已发布的房屋列表
     */
    @Select("select h.id,h.houseid,h.htitle,h.hfloor,h.himg,h.hadr,h.hrent,h.state,hs.name hstate, " +
            "h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate, " +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc from house h,house_state hs " +
            "where h.state=hs.id and h.state=0 and h.hadr like '%${hadr}%'")
    List<House> houseList(House house, RowBounds rowBounds);

    /**
     * 获取已发布房源列表总数
     */
    @Select("select count(1) from house where state=0 and hadr like '%${hadr}%'")
    Integer count(House house);

    /**
     * 后台数据表格获取全部房屋列表
     */
    @Select("select h.id,h.houseid,c.cname landlord,h.htitle,h.hfloor,h.himg,h.state,h.hadr,h.hrent, " +
            "hs.name hstate,h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate," +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc from house h,customer c,house_state hs " +
            "where h.landlord=c.id and h.state=hs.id and h.hadr like '%${hadr}%'")
    List<House> houseBackList(House house, RowBounds rowBounds);

    /**
     * 获取房源列表总数
     */
    @Select("select count(1) from house where hadr like '%${hadr}%'")
    Integer BackListcount(House house);

    /**
     * 获取所有房屋状态
     *
     * @return
     */
    @Select("select * from house_state")
    List<HouseState> getAllHosState();

    /**
     * 获取所有联系房源
     *
     * @param leadHouse
     * @return
     */
    @Select("select l.*,ls.name lstate from leadhouse l,leadhouse_state ls " +
            "where l.state=ls.id and ladr like '%${ladr}%'")
    List<LeadHouse>
    getAllLeadhouse(LeadHouse leadHouse, RowBounds rowBounds);

    /**
     * 获取联系房源总条数
     *
     * @param leadHouse
     * @return
     */
    @Select("select count(1) from leadhouse where ladr like '%${ladr}%' ")
    Integer leaCount(LeadHouse leadHouse);

    /**
     * 根据ID修改leadhouse房源状态为已联系(1)
     */
    @Update("update leadhouse set state=1 where id=#{id}")
    Integer updateLstate(Integer id);

    /**
     * 获取房屋对应的描述标签
     */
    @Select("select l.labelcontent labelcontent from house h left join house_label hl " +
            "on h.houseid=hl.houseid join label l on hl.labelid=l.labelid " +
            "where h.houseid=#{houseid}")
    List<String> lableList(Integer houseid);

    /**
     * 根据ID获取房屋详细信息
     */
    @Select("select h.id,h.houseid,h.hadr,h.agentid,h.htitle,h.hrent,h.state,h.hfloor, " +
            "h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate, " +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc,h.longitude,h.latitude " +
            "from house h where h.id=#{id}")
    House houseDetail(Integer id);

    /**
     * 根据房屋ID获取相应图片列表
     */
    @Select("select houseimg from houseimgs where houseid=#{houseid}")
    List<String> houseImgs(Integer houseid);

    /**
     * 根据房屋ID获取对应的配套设施
     */
    @Select("select f.furniturename from house h,house_furniture hf,furniture f " +
            "where h.houseid=hf.houseid and hf.furnitureid=f.furnitureid " +
            "and h.houseid=#{houseid}")
    List<String> houseInstallation(Integer houseid);

    /**
     *
     * 查询所有客户信息
     */
    @Select("select * from customer")
    List<Customer> cnumberAll();

    /**
     * sun
     * 根据房东编号cnumber查询房东id
     */
    @Select("select id from customer where cnumber=#{cnumber}")
    Integer cnumberById(Integer cnumber);

    /**
     * sun
     * 往house_furniture房屋设施中间表中添加数据
     */

    @Insert("insert into house_furniture(houseid,furnitureid) values(#{houseid},#{furnitureid})")
    Integer housefurInsert(Integer houseid, Integer furnitureid);

    /**
     * sun
     * 根据furniturename查询到furniture里对应的furnitureid
     */
    @Select("select furnitureid from furniture where furniturename=#{furniturename}")
    Integer furnitureSelect(String furniturename);

    /**
     * sun
     * 往house_label房屋设施中间表中添加数据
     */
    @Insert("insert into house_label(houseid,labelid) values(#{houseid},#{labelid})")
    Integer houselabelInsert(Integer houseid, Integer labelid);

    /**
     * sun
     * 根据labelcontent查询到label里对应的labelid
     */
    @Select("select labelid from label where labelcontent=#{labelcontent}")
    Integer labelInsert(String labelcontent);

    /**
     * sun
     * 根据cnumber房东编号修改customer表中房东信息
     */
    @Update("update customer set cname=#{cname},ccard=#{ccard},csex=#{csex} where id=#{id}")
    Integer updateCustomer(Customer customer);

    /**
     * sun
     * 根据房屋id查询房屋记录，验证房屋id是否重复
     */
    @Select("select count(*) from house where houseid=#{houseid}")
    Integer houseById(Integer houseid);

    /**
     * sun
     * 往house表中添加房源信息并将其状态定为待审核2
     */
    @Insert("insert into house(htitle,hadr,houseid,hfloor,orientation,hdesc,hspace, " +
            "hrent,releasedate,state,landlord,agentid,cimg,himg) values(#{htitle},#{hadr}, " +
            "#{houseid},#{hfloor},#{orientation},#{hdesc},#{hspace},#{hrent},#{releasedate}, " +
            "2,#{landlord},#{agentid},#{cimg},#{himg})")
    Integer houseInsert(House house);

    /**
     * sun
     * 往leadhouse表中添加发布房源信息，并将经纪人和提供房源的客户的状态改为未联系2
     */
    @Insert("insert into leadhouse(ladr,remoney,lname,lphone,state) values(#{ladr}, " +
            "#{remoney},#{lname},#{lphone},2)")
    Integer leadhouseInsert(LeadHouse leadHouse);

    /**
     * sun
     * 往houseimgs表中插入房屋组图路径
     */
    @Insert("insert into houseimgs(houseid,houseimg) values(#{houseid},#{houseimg})")
    Integer houseimgsInsert(HouseImgs houseImgs);

    /**
     * 根据ID获取经纪人信息
     */
    @Select("select * from employee where id=#{id}")
    Employee getAgentInfo(Integer id);

    /**
     * 根据ID删除房屋信息
     */
    @Delete("delete from house where id=#{id}")
    int delHouseById(Integer id);

    /**
     * 根据ID修改房源信息
     */
    @Update("update house set htitle=#{htitle},hadr=#{hadr},hrent=#{hrent},himg=#{himg}, " +
            "state=#{state} where houseid=#{houseid}")
    int updateHouseInfo(Houses house);

    /**
     * 获取待审核的房源信息，即查询房屋状态为待审核2
     */
    @Select("select h.id,h.houseid,c.cname landlord,h.htitle,h.state,h.hfloor,h.himg,h.hadr,h.hrent," +
            "hs.name hstate,h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate," +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc from house h,customer c,house_state hs " +
            "where h.landlord=c.id and h.state=hs.id and h.state=2 and h.hadr like '%${hadr}%'")
    List<House> unauditedHouse(House house, RowBounds rowBounds);

    /**
     * 获取待审核房源总数
     */
    @Select("select count(1) from house where state=2 and hadr like '%${hadr}%'")
    Integer unauditCount(House house);

    /**
     * 更改待审核的房源状态为已审核(4)
     */
    @Update("update house set state=4 where id=#{id}")
    int updateStateOne(Integer id);

    /**
     * 更改待审核的房源状态为未通过(3)
     */
    @Update("update house set state=3,examine=#{examine} where id=#{id}")
    int updateStateTwo(House house);

    /**
     * 更改已审核的房源状态为已发布(0)
     */
    @Update("update house set state=0 where id=#{id}")
    int updateStateThree(Integer id);

    /**
     * 更改已发布的房源状态为待发布(1)
     */
    @Update("update house set state=1 where id=#{id}")
    int updateStateFour(Integer id);

    /**
     * 根据ID修改房源信息(再次提交)
     *
     * @param house
     */
    @Update("update house set htitle=#{htitle},hadr=#{hadr},hrent=#{hrent},himg=#{himg}, " +
            "state=2,examine=null where id=#{id}")
    int updateTwoHouse(House house);

    /**
     * 获取对应经纪人的房源
     */
    @Select("select h.*,hs.name hstate from house h left join house_state hs " +
            "on h.state=hs.id where h.agentid=#{agentid} and hadr like '%${hadr}%'")
    List<House> agentHouse(House house, RowBounds rowBounds);

    /**
     * 获取对应经纪人的房源总数
     */
    @Select("select count(1) from house where agentid=#{agentid} and hadr like '%${hadr}%'")
    Integer agentHouseCount(House house);

    /**
     * 查看租客
     *
     * @param id
     * @return
     */
    @Select("select c.cname,c.cphone,c.ccard,DATE_FORMAT(r.begintime,'%Y-%m-%d') begintime," +
            "DATE_FORMAT(r.endtime,'%Y-%m-%d') endtime,r.charge from customer c left join " +
            "house h on c.id=h.tenantid left join rented r on " +
            " c.id=r.renter where h.id=#{id}")
    List<RentedandCus> getAllRenCus(Integer id);

    /**
     * 关注房源
     */
    @Insert("insert into FollowHouse (cid,houseid) values (#{cid},#{houseid})")
    int followhouse(Integer cid, Integer houseid);

    /**
     * 判断是否已经关注
     */
    @Select("select * from followhouse where cid=#{cid} and houseid=#{houseid}")
    FollowHouse isFollow(Integer cid, Integer houseid);

    /**
     * 用户关注的房源
     */
    @Select("select h.id,h.houseid,h.htitle,h.hfloor,h.himg,h.hadr,h.hrent,h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate,DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc from house h,house_state hs,followhouse f where h.state=hs.id and f.houseid=h.id and h.state=0 and f.cid=#{cid}")
    List<House> myFollowHouse(Integer cid);

    /**
     * 用户关注房源的总数
     */
    @Select("select count(f.id) from house h, followhouse f where h.id=f.houseid and h.state=0 " +
            "and f.cid=#{cid}")
    int myFollowHouseCount(Integer cid);

    /**
     * 取消关注
     */
    @Delete("delete from followhouse where cid=#{cid} and houseid=#{houseid}")
    int delFollow(Integer cid, Integer houseid);

    /**
     * 后台发布的房源
     */
    @Select("select h.id,h.houseid,h.htitle,h.hfloor,h.himg,h.hadr,h.hrent," +
            "h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate," +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc from house h,house_state hs " +
            "where h.state=hs.id and h.state=0 and h.landlord=#{landlord}")
    List<House> myPostedHouse(Integer landlord);

    /**
     * 后台发布的房源总数
     */
    @Select("select count(1) from house where state=0 and landlord=#{landlord}")
    int myPostedHouseCount(Integer landlord);


    /**
     * 查询当前用户名下租赁的房子（乔 新增）
     * @param tenantid
     * @return
     */
    @Select("select h.id,h.houseid,h.htitle,h.hfloor,h.himg,h.hadr,h.hrent," +
            "h.hspace,h.orientation,DATE_FORMAT(h.releasedate,'%Y-%m-%d') releasedate," +
            "DATEDIFF(NOW(),h.releasedate) newstime,h.hdesc" +
            " from house h,customer c" +
            " where h.tenantid=c.id and h.state=5 and h.tenantid=#{tenantid}")
    List<House> myRentHouse(Integer tenantid);

    /**
     * 查询当前用户名下租赁房子的数量（乔 新增）
     * @param tenantid
     * @return
     */
    @Select("select count(*) from house h,customer c where " +
            "h.tenantid=c.id and h.state=5 and h.tenantid=#{tenantid}")
    int myRentHouseCount(Integer tenantid);

    /**
     * 查询报修填写的信息
     * @param id
     * @return
     */
    @Select("select h.houseid,h.tenantid,c.cname,h.hadr" +
            " from house h,customer c" +
            " where  h.tenantid=c.id and h.id=#{id}")
    Map myRepairs(Integer id);



}
