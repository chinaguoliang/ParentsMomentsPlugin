package com.jgkj.plugin.services;

import com.jgkj.plugin.domain.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by chenguoliang on 2016/10/6.
 */
@Mapper
public interface LocationMapper {
    @Select("select * from plugin_location where school_id = #{school_id}")
    Location findUserBySchoolId(@Param("school_id") String schoolId);
}
