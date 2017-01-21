package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.ResponseObj;
import com.jgkj.plugin.domain.VideoInfo;
import com.jgkj.plugin.repositories.VideoInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chen on 17/1/21.
 */
@Controller
public class VideoInfoController implements Serializable{
    @Autowired
    VideoInfoMapper videoInfoMapper;

    @RequestMapping("/findVideoInfoBySchoolId")
    @ResponseBody
    public ResponseObj findVideoInfoBySchoolId(@Param("schoolid") String schoolid) {
        ResponseObj ro = new ResponseObj();
        List<VideoInfo> videoInfoList;
        try {
            videoInfoList = videoInfoMapper.findVideoInfoBySchoolId(schoolid);
            ro.setObj(videoInfoList);
            ro.setMsg("获取视频信息成功");
            ro.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ro.setObj(null);
            ro.setMsg("获取视频信息失败");
            ro.setSuccess(false);

        } finally {
        }

        return ro;
    }


    @RequestMapping("/findVideoInfoBySchoolIdClassId")
    @ResponseBody
    public ResponseObj findVideoInfoBySchoolIdClassId(@Param("schoolid") String schoolid,@Param("classid") String classid) {
        ResponseObj ro = new ResponseObj();
        List<VideoInfo> videoInfoList;
        try {
            videoInfoList = videoInfoMapper.findVideoInfoBySchoolIdClassId(schoolid,classid);
            ro.setObj(videoInfoList);
            ro.setMsg("获取视频信息成功");
            ro.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ro.setObj(null);
            ro.setMsg("获取视频信息失败");
            ro.setSuccess(false);
        } finally {

        }

        return ro;
    }


    @RequestMapping("/addOneVideoInfo")
    @ResponseBody
    public ResponseObj addOneVideoInfo(@Param("schoolid") String schoolid,@Param("classid") String classid,@Param("serialnum") String serialnum) {
        ResponseObj ro = new ResponseObj();
        List<VideoInfo> videoInfoList;
        try {
            int result = videoInfoMapper.addOneVideoInfo(null,schoolid,classid,serialnum);
            if (result > 0) {
                ro.setObj(null);
                ro.setMsg("保存视频信息成功");
                ro.setSuccess(true);
            } else {
                ro.setObj(null);
                ro.setMsg("保存视频信息失败");
                ro.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ro.setObj(null);
            ro.setMsg("保存视频信息失败");
            ro.setSuccess(false);
        } finally {

        }

        return ro;
    }

}
