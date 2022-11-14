package com.hcr.turtle.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;
import java.util.Map;

/**
 * 租房合同
 * @CacheNamespace注解主要用于mybatis二级缓存
 * 全注解的方式，和xml只能只用一个
 */
@CacheNamespace(implementation = RedisCache.class)
public interface ContractMapper {

    @Select("select e.ename,e.ecard," +
            "c.cname,c.ccard," +
            "r.begintime,r.endtime," +
            "h.hadr,r.hrend"+
            " from customer c "+
            " left join rented r on r.renter=c.id" +
            " left join house h on h.id=r.houseid" +
            " left join employee e on h.agentid=e.id where h.id=#{hid}")
    List<Map<String,Object>> getContract(Integer hid);

}
