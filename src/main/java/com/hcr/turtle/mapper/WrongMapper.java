package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.Wrong;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * fileName:WrongMapper
 * author:Qiao
 * createTime:2019/7/31
 * version:1.0.0
 */

/**
 * 报障操作（乔 新增）
 */
@Repository
public interface WrongMapper {
    /**
     * 查询所有未处理报障的房屋列表
     * @param wrong
     * @param rowBounds
     * @return
     */
    @Select("select w.id,w.houseid,w.tenantid,w.tenant,w.hadr,w.data,w.detail,w.state,w.optdata,ws.name " +
            "from wrong w, wrong_state ws where w.state=ws.id and w.state=1 and w.tenant like '%${tenant}%'")
    List<Wrong> wrongList(Wrong wrong, RowBounds rowBounds);

    /**
     * 报障房屋的总个数
     * @param wrong
     * @return
     */
    @Select("select count(*) from wrong where state=1 and tenant like '%${tenant}%'")
    Integer wrongCount(Wrong wrong);

    /**
     * 根据id修改房屋的处理状态
     * @param id
     * @return
     */
    @Update("update wrong set state=2 where id=#{id}")
    Integer updateWrong(Integer id);

    /**
     * 查询所有已处理报障的房屋列表
     * @param wrong
     * @param rowBounds
     * @return
     */
    @Select("select w.id,w.houseid,w.tenantid,w.tenant,w.hadr,w.data,w.detail,w.state,w.optdata,ws.name " +
            "from wrong w, wrong_state ws where w.state=ws.id and w.state=2 and w.tenant like '%${tenant}%'")
    List<Wrong> wrong2List(Wrong wrong, RowBounds rowBounds);

    /**
     * 报障房屋的总个数
     * @param wrong
     * @return
     */
    @Select("select count(*) from wrong where state=2 and tenant like '%${tenant}%'")
    Integer wrong2Count(Wrong wrong);

    /**
     * 添加报修的信息
     * @param map
     * @return
     */
    @Insert("insert wrong(houseid ,tenantid ,tenant,hadr,detail) values(" +
            "#{houseid},#{tenantid},#{cname},#{hadr},#{detail}) ")
    Integer saveWrong(Map map);
}
