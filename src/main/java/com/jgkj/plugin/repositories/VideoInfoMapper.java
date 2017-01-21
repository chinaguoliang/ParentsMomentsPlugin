package com.jgkj.plugin.repositories;

import com.jgkj.plugin.domain.MyLocation;
import com.jgkj.plugin.domain.VideoInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chen on 17/1/21.
 */

@Mapper
public interface VideoInfoMapper {
    @Select("select * from plugin_video_info where school_id = #{schoolid}")
    List<VideoInfo> findVideoInfoBySchoolId(@Param("schoolid") String schoolid);

    @Select("select * from plugin_video_info where school_id = #{schoolid} and class_id = #{classid}")
    List<VideoInfo> findVideoInfoBySchoolIdClassId(@Param("schoolid") String schoolid,@Param("classid") String classid);

    @Insert("insert into plugin_video_info values(#{id} , #{schoolid}, #{classid} , #{serialnum})")
    public int addOneVideoInfo(@Param("id") String id,@Param("schoolid") String schoolid,@Param("classid") String classid,@Param("serialnum") String serialnum);

}
