package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 菜单操作
 */
public interface MenuMapper {

    /**一二级菜单
     * @param eid
     * @return
     */
    @Select("select distinct c.id id, c.title title ,c.url url ,c.icon icon,c.pid pid " +
            "from permission c, role_per rc,employee e where c.id = rc.pid " +
            "and  rc.rid = e.roleid and e.id=#{eid} order by c.pid,c.id ASC")
    public List<Permission> getMenuTree(Integer eid);

    /**获取所有父菜单
     * @param permission
     * @param rowBounds
     * @return
     */
    @Select("select * from permission where pid=0 and pnum like '%${pnum}%' ")
    public List<Permission> getFatherMenu(Permission permission, RowBounds rowBounds);


    /**获取总条数
     * @param permission
     * @return
     */
    @Select("select count(1) from permission where pid=0 and pnum like '%${pnum}%' ")
    public int FatherMenuCount(Permission permission);

    /**添加父菜单
     * @param permission
     * @return
     */
    @Insert("insert into permission (pnum,pid,title,icon) values (#{pnum},0,#{title},#{icon}) ")
    public int addFatherMenu(Permission permission);

    /**修改父菜单信息
     * @param permission
     * @return
     */
    @Update("update permission set pnum=#{pnum},title=#{title},icon=#{icon} where id=#{id}")
    public int updateFatherMenu(Permission permission);


    /**删除父菜单与其下的子菜单
     * @param
     * @return
     */
    @Delete("delete from permission where id=#{id} or pid=#{id}")
    public int deleteFatherMenu(Integer id);

    /**获取所有子菜单
     * @param permission
     * @param rowBounds
     * @return
     */
    @Select("select a.id id,a.pnum pnum,a.pid pid,b.title btitle,a.title title,a.url url " +
            "from permission a,permission b where a.pid=b.id and a.pid!=0 and a.pnum like '%${pnum}%' ")
    public List<Permission> getSonMenu(Permission permission, RowBounds rowBounds);

    /**获取总条数
     * @param permission
     * @return
     */
    @Select("select count(1) from permission where pid!=0 and pnum like '%${pnum}%' ")
    public int SonMenuCount(Permission permission);

    /**添加子菜单
     * @param permission
     * @return
     */
    @Insert("insert into permission (pnum,pid,title,url) values (#{pnum},#{pid},#{title},#{url}) ")
    public int addSonMenu(Permission permission);

    /**修改子菜单信息
     * @param permission
     * @return
     */
    @Update("update permission set pnum=#{pnum},pid=#{pid},title=#{title},url=#{url} where id=#{id}")
    public int updateSonMenu(Permission permission);

    /**删除子菜单
     * @param
     * @return
     */
    @Delete("delete from permission where id=#{id}")
    public int deleteSonMenu(Integer id);
}
