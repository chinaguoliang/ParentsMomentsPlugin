package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.domain.ResponseObj;
import com.jgkj.plugin.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 16/10/19.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
public class LocationController extends BaseController{

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping("/attendance/getLocation")
    @ResponseBody
    public Location getSchoolLocation(@RequestParam("id") long id) {
        Location location = (Location) locationRepository.findOne(id);
        return location;
    }

    @RequestMapping("/attendance/getAllLocation")
    @ResponseBody
    public ResponseObj getSchoolLocation() {
        ResponseObj ro = new ResponseObj();
        try {
            List<Location> locationList = locationRepository.findAll();
            ro.setObj(locationList);
            ro.setMsg("查询位置成功");
            ro.setSuccess(true);
            return ro;
        } catch (Exception e) {
            e.printStackTrace();

            ro.setObj(new ArrayList<String>());
            ro.setMsg("查询失败");
            ro.setSuccess(false);
        }
        return ro;
    }


    @RequestMapping("/attendance/saveLocation")
    @ResponseBody
    public ResponseObj saveSchoolLocation(@RequestParam("schoolid") int schoolId,@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) {

        ResponseObj ro = new ResponseObj();
        try {
            Location location = new Location();
            location.setSchool_id(schoolId);
            location.setLongitude(longitude);
            location.setLatitude(latitude);
            Location locationResult = (Location) locationRepository.save(location);
            if (locationResult != null) {
                ro.setMsg("保存位置成功");
                ro.setSuccess(true);
            } else {
                ro.setMsg("保存位置失败");
                ro.setSuccess(false);
            }
            ro.setObj("");
        } catch (Exception e) {
            e.printStackTrace();
            ro.setMsg("保存位置失败");
            ro.setSuccess(false);
            ro.setObj("");
        }
        return ro;
    }


    @RequestMapping("/attendance/updateLocation")
    @ResponseBody
    public ResponseObj updateSchoolLocation(@RequestParam("id") long id,@RequestParam("schoolid") int schoolId,@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) {

        ResponseObj ro = new ResponseObj();
        try {
            Location location = new Location();
            location.setId(id);
            location.setSchool_id(schoolId);
            location.setLongitude(longitude);
            location.setLatitude(latitude);
            Location locationResult = (Location) locationRepository.save(location);
            if (locationResult != null) {
                ro.setMsg("更新位置成功");
                ro.setSuccess(true);
            } else {
                ro.setMsg("更新位置失败");
                ro.setSuccess(false);
            }
            ro.setObj("");
        } catch (Exception e) {
            e.printStackTrace();
            ro.setMsg("更新位置失败");
            ro.setSuccess(false);
            ro.setObj("");
        }
        return ro;
    }


    @RequestMapping("/attendance/getLocationBySchoolId")
    @ResponseBody
    public ResponseObj getLocationBySchoolId(@RequestParam("schoolid") final int schoolId) {
        ResponseObj ro = new ResponseObj();
        try {
            Specification<Location> specification = new Specification<Location>() {
                @Override
                public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    Predicate condition = criteriaBuilder.equal(root.get("school_id"), schoolId);
                    return criteriaBuilder.and(condition);
                }
            };
            Location location = (Location) locationRepository.findOne(specification);
            if (location != null) {
                ro.setMsg("查询位置成功");
                ro.setSuccess(true);
                ro.setObj(location);
            } else {
                ro.setMsg("未查询到相关学校位置信息");
                ro.setSuccess(true);
                ro.setObj("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            ro.setMsg("查询失败");
            ro.setSuccess(false);
            ro.setObj(new Object());
        }
        return ro;
    }


    //post方式请求获取数据
    @RequestMapping(value = "/testLocation/post")
    @ResponseBody
    public String mytest(@RequestParam("schoolId") String schoolId) {
        if (schoolId == null || "".equals(schoolId)) {
            return "params is empty";
        } else {
            return schoolId;
        }
    }

    //get方式请求获取数据
    @RequestMapping(value = "/testLocation/get")
    @ResponseBody
    public String mytest1(@RequestParam("schoolId") String schoolId) {
        if (schoolId == null || "".equals(schoolId)) {
            return "params is empty";
        } else {
            return schoolId;
        }
    }


//    public Location findAllByIdAndName(final Long id, final String name) {
//        Specification<Location> specification = new Specification<Location>() {
//            @Override
//            public Predicate toPredicate(Root<Location> root,
//                                         CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<Predicate>();
//                Path<Long> $id = root.get("id");
//                Predicate _id = criteriaBuilder.equal($id, id);
//                predicates.add(_id);
//                Path<Long> $name = root.get("name");
//                Predicate _name = criteriaBuilder.equal($name, name);
//                predicates.add(_name);
//                return criteriaBuilder.and(predicates
//                        .toArray(new Predicate[] {}));
//            }
//        };
//        return locationRepository.findOne(specification);
//    }
}
