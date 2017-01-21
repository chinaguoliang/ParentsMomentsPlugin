package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.ResponseObj;
import com.jgkj.plugin.domain.VideoInfo;
import com.jgkj.plugin.domain.VideoTimeControl;
import com.jgkj.plugin.repositories.LocationRepository;
import com.jgkj.plugin.repositories.VideoInfoMapper;
import com.jgkj.plugin.repositories.VideoTimeControllRepository;
import com.jgkj.plugin.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 16/10/20.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class VideoController {
    @Autowired
    VideoTimeControllRepository videoTimeControllRepository;

    @Autowired
    VideoInfoMapper videoInfoMapper;

    @RequestMapping("/videoTime/saveVideoControlTime")
    @ResponseBody
    public ResponseObj saveVideoControlTime(@RequestParam("schoolid") int schoolId, @RequestParam("classid") int classId, @RequestParam("start_time") String startTime, @RequestParam("end_time") String endTime, @RequestParam("is_allow_play") int isAllowPlay,@RequestParam("serial_number") int serialNumber) {
        VideoTimeControl videoTimeControl = getVideoControl(schoolId, classId);
        ResponseObj ro = new ResponseObj();

        try {
            VideoTimeControl addVideoTimeControl = new VideoTimeControl();
            if (videoTimeControl != null) {
                //增加
                addVideoTimeControl.setId(videoTimeControl.getId());
            }

            addVideoTimeControl.setSchool_id(schoolId);
            addVideoTimeControl.setClass_id(classId);
            addVideoTimeControl.setEnd_time(endTime);
            addVideoTimeControl.setStart_time(startTime);
            addVideoTimeControl.setIs_allow_play(isAllowPlay);
            addVideoTimeControl.setSerial_number(serialNumber);
            VideoTimeControl addResult = (VideoTimeControl) videoTimeControllRepository.save(addVideoTimeControl);
            if (addResult != null) {
                ro.setObj("");
                ro.setMsg("更新时间成功");
                ro.setSuccess(true);
            } else {
                ro.setObj("");
                ro.setMsg("更新时间失败");
                ro.setSuccess(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            ro.setObj("");
            ro.setMsg("更新位置失败");
            ro.setSuccess(false);
        }

        return ro;
    }

    @RequestMapping("/videoTime/getVideoControlTime")
    @ResponseBody
    public ResponseObj getVideoControlTime(@RequestParam("schoolid") int schoolId, @RequestParam("classid") int classId) {
        VideoTimeControl videoTimeControl = null;
        List<VideoInfo> videoInfoList = null;
        List <VideoTimeControl> videoControllList = null;
        if (classId == -1 || "".equals(classId)) {
            //权限是园长
            videoControllList = getVideoControlForSchoolId(schoolId);
            videoInfoList = videoInfoMapper.findVideoInfoBySchoolIdClassId(String.valueOf(schoolId),String.valueOf(classId));

        } else {
            //权限是教师或者家长
            videoTimeControl = getVideoControl(schoolId, classId);
            videoInfoList = videoInfoMapper.findVideoInfoBySchoolId(String.valueOf(schoolId));
        }



        ResponseObj ro = new ResponseObj();
        try {
            if (videoTimeControl == null) {
                VideoTimeControl saveVideoTimeControl = new VideoTimeControl();
                saveVideoTimeControl.setSchool_id(schoolId);
                saveVideoTimeControl.setClass_id(classId);
                saveVideoTimeControl.setIs_allow_play(0);

                saveVideoTimeControl.setStart_time(DateUtils.DEFAULT_START_TIME);
                saveVideoTimeControl.setEnd_time(DateUtils.DEFAULT_END_TIME);
                VideoTimeControl saveResult = (VideoTimeControl) videoTimeControllRepository.save(saveVideoTimeControl);
//                long dateTime[] = DateUtils.getWorkTimeOneDay("", "");
//                saveVideoTimeControl.setStart_time(dateTime[0] + "");
//                saveVideoTimeControl.setEnd_time(dateTime[1] + "");
                if (saveResult != null) {
                    ro.setObj(saveVideoTimeControl);
                    ro.setMsg("获取数据成功");
                    ro.setSuccess(true);
                } else {
                    ro.setObj("");
                    ro.setMsg("获取数据失败");
                    ro.setSuccess(false);
                }
            } else {
                ro.setObj(videoTimeControl);
                ro.setMsg("获取数据成功");
                ro.setSuccess(true);
            }

            ro.setVideoInfo(videoInfoList);
            ro.setVideoControllList(videoControllList);
        } catch (Exception e) {
            e.printStackTrace();
            ro.setObj("");
            ro.setMsg("获取数据失败");
            ro.setSuccess(false);
        }
        return ro;
    }


    private VideoTimeControl getVideoControl(final int schoolId, final int classId) {
        Specification<VideoTimeControl> specification = new Specification<VideoTimeControl>() {
            @Override
            public Predicate toPredicate(Root<VideoTimeControl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> schoolIdPath = root.get("school_id");
                Path<String> classIdPath = root.get("class_id");

                return criteriaBuilder.and(criteriaBuilder.equal(schoolIdPath, schoolId), criteriaBuilder.equal(classIdPath, classId));
            }
        };

        VideoTimeControl videoTimeControl = (VideoTimeControl) videoTimeControllRepository.findOne(specification);
        return videoTimeControl;
    }


    private List<VideoTimeControl> getVideoControlForSchoolId(final int schoolId) {
        Specification<VideoTimeControl> specification = new Specification<VideoTimeControl>() {
            @Override
            public Predicate toPredicate(Root<VideoTimeControl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> schoolIdPath = root.get("school_id");

                return criteriaBuilder.and(criteriaBuilder.equal(schoolIdPath, schoolId));
            }
        };

        List<VideoTimeControl> videoControllList = videoTimeControllRepository.findAll(specification);
        return videoControllList;
    }


    @RequestMapping("/videoTime/getVideoControlTimeBySchoolId")
    @ResponseBody
    public ResponseObj getVideoControlTimeBySchoolId(final @RequestParam("schoolid") int schoolId) {
        Specification<VideoTimeControl> specification = new Specification<VideoTimeControl>() {
            @Override
            public Predicate toPredicate(Root<VideoTimeControl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> schoolIdPath = root.get("school_id");

                return criteriaBuilder.and(criteriaBuilder.equal(schoolIdPath, schoolId));
            }
        };
        List<VideoTimeControl> videoControllList = videoTimeControllRepository.findAll(specification);
        ResponseObj ro = new ResponseObj();
        if (videoControllList != null && videoControllList.size() > 0) {
            ro.setObj(videoControllList);
            ro.setMsg("获取数据成功");
            ro.setSuccess(true);
        } else {
            ro.setObj(new ArrayList<VideoTimeControl>());
            ro.setMsg("获取数据成功");
            ro.setSuccess(true);
        }
        return  ro;
    }
}
