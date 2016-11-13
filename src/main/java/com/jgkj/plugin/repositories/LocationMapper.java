package com.jgkj.plugin.repositories;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.domain.MyLocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chenguoliang on 2016/10/6.
 */
@Mapper
public interface LocationMapper {
    @Select("select * from plugin_location where id = #{id}")
    MyLocation findOneLocation(@Param("id") String id);

    @Select("select * from plugin_location")
    List<MyLocation> findAllLocation();

}
