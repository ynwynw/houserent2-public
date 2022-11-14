package com.hcr.turtle.mapper;

import com.hcr.turtle.entiey.Permission;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

/**
 * 权限操作
 * @CacheNamespace开启了二级缓存
 */
@CacheNamespace(implementation = RedisCache.class)
public interface PermissionMapper {


    /**获取所有权限信息
     * @return
     */
    @Select("select distinct c.id id, c.title title,c.pid pid "+
            "from permission c order by c.pid,c.id ASC ")
    public List<Permission> getAllPer();


    /**根据rid获取对应的pid
     * @param id
     * @return
     */
    @Select("select pid id from role_per r where rid=#{id}")
    public Integer[] getPidByRid(Integer id);

    /**根据rid删除对应权限
     * @param rid
     * @return
     */
    @Delete("delete from role_per where rid=#{rid}")
    public int deletePer(Integer rid);

    /**增加权限
     * @param rid
     * @param pid
     * @return
     */
    @Insert("insert into role_per (rid,pid) values(#{rid},#{pid})")
    public  int addPer(Integer rid, Integer pid);


}
