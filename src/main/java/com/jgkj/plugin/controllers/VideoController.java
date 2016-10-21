package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.ResponseObj;
import com.jgkj.plugin.domain.VideoTimeControl;
import com.jgkj.plugin.repositories.LocationRepository;
import com.jgkj.plugin.repositories.VideoTimeControllRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;

/**
 * Created by chen on 16/10/20.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class VideoController {
    @Autowired
    VideoTimeControllRepository videoTimeControllRepository;

    @RequestMapping("/videoTime/saveVideoControlTime")
    @ResponseBody
    public ResponseObj saveSchoolLocation(@RequestParam("schoolid") int schoolId, @RequestParam("classid") int classId,@RequestParam("start_time") int startTime,@RequestParam("end_time") int endTime,@RequestParam("is_allow_play") int isAllowPlay) {
        VideoTimeControl videoTimeControl = getVideoControl(schoolId,classId);
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
            VideoTimeControl addResult= (VideoTimeControl) videoTimeControllRepository.save(addVideoTimeControl);
            if (addResult != null) {
                ro.setObj("");
                ro.setMsg("更新位置成功");
                ro.setSuccess(true);
            } else {
                ro.setObj("");
                ro.setMsg("更新位置失败");
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

    private VideoTimeControl getVideoControl(final int schoolId,final int classId) {
        Specification<VideoTimeControl> specification = new Specification<VideoTimeControl>() {
            @Override
            public Predicate toPredicate(Root<VideoTimeControl> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<String> schoolIdPath = root.get("school_id");
                Path<String> classIdPath = root.get("class_id");

                return criteriaBuilder.and(criteriaBuilder.equal(schoolIdPath,schoolId),criteriaBuilder.equal(classIdPath,classId));
            }
        };

        VideoTimeControl videoTimeControl = (VideoTimeControl) videoTimeControllRepository.findOne(specification);
        return videoTimeControl;
    }
}
