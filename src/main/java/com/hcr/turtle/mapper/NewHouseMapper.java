package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.House;
import com.hcr.turtle.entiey.Rented;
import com.hcr.turtle.entiey.Seehistory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface NewHouseMapper {

    /**预约看房
     * @param seehistory
     * @return
     */
    @Insert("insert into seehistory(renter,houseid,state) values(#{renter},#{houseid},1)")
    public int addHosofCus(Seehistory seehistory);

    /**
     * 获取对应经纪人的房源(已发布房源)
     */
    @Select("select h.*,hs.name hstate from house h left join house_state hs " +
            "on h.state=hs.id where h.agentid=#{agentid} and h.state=0 " +
            "and hadr like '%${hadr}%'")
    List<House> getEmpHos(House house, RowBounds rowBounds);

    /**
     * 获取对应经纪人的房源总数
     */
    @Select("select count(1) from house where agentid=#{agentid} and hadr like '%${hadr}%'")
    Integer EmpHosCount(House house);

    /**获取预约人数
     * @param id
     * @return
     */
    @Select("select count(distinct renter) lesscount from seehistory where houseid=#{id} ")
    Integer getLessCount(Integer id);

    /**根据房源id获取对应预约人资料，联合客户表，预约看房的客户表和房源表（house,seehistory,customer）
     * @param id
     * @return
     */
    @Select("select distinct s.renter,s.id,s.houseid,DATE_FORMAT(s.seetime,'%Y-%m-%d') seetime, " +
            "s.cusdesc,s.state,c.cname,c.cphone,c.ccard,c.csex,ss.name sstate,h.hrent from seehistory s " +
            "left join customer c on c.id=s.renter left join house h on s.houseid=h.id left join " +
            "seehistory_state ss on s.state=ss.id where s.houseid=#{id}")
    List<Seehistory> getLessByHosId(Integer id);

    /**修改客户预约信息(已看房)
     * @param id
     * @return
     */
    @Update("update seehistory set state=2 where id=#{id}")
    int upLessState(Integer id);

    /**根据id修改客户身份证号
     * @param seehistory
     * @return
     */
    @Update("update customer set ccard=#{ccard} where id=#{renter}")
    int upCusCardByid(Seehistory seehistory);

    /**根据id修改看房记录，补充看房时间，租户描述及修改租户意向
     * @param seehistory
     * @return
     */
    @Update("update seehistory set seetime=#{seetime},cusdesc=#{cusdesc},state=#{state} where id=#{id}")
    int upSeeByid(Seehistory seehistory);

    /**
     * 添加一条租房记录，在rented表里面添加一条租房信息
     */
    @Insert("insert into rented (renter,houseid,begintime,endtime,hrend,charge) " +
            "values (#{renter},#{houseid},#{begintime},#{endtime},#{hrend},#{charge})")
    int insertRented(Rented rented);

    /**
     * 根据ID修改已发布的房屋状态为已出租(4)
     * 给house表补充租客的ID（house中的tenantid）
     */
    @Update("update house set state=5,tenantid=#{tenantid} where id=#{id}")
    int updateThree(Integer tenantid, Integer id);

    /**根据房子id删除看房记录
     * @param id
     * @return
     */
    @Delete("delete from seehistory where houseid=#{id}")
    int deleSee(Integer id);

    /**根据用户id删除看房记录
     * @param id
     * @return
     */
    @Delete("delete from seehistory where renter=#{id}")
    int deleSeeByCusId(Integer id);


}
