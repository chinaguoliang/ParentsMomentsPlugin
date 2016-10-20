package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.domain.ResponseObj;
import com.jgkj.plugin.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    BaseRepository baseRepository;

    @RequestMapping("/attendance/getLocation")
    @ResponseBody
    public Location getSchoolLocation(@RequestParam("id") long id) {
        Location location = baseRepository.findOne(id);
        return location;
    }

    @RequestMapping("/attendance/getAllLocation")
    @ResponseBody
    public ResponseObj getSchoolLocation() {
        ResponseObj ro = new ResponseObj();
        try {
            List<Location> locationList = baseRepository.findAll();
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
    public Location saveSchoolLocation(@RequestParam("schoolid") int schoolId,@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude) {
        Location location = new Location();
        location.setSchool_id(schoolId);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return baseRepository.save(location);
    }


    @RequestMapping("/attendance/getLocationBySchoolId")
    @ResponseBody
    public Location getLocationBySchoolId(@RequestParam("schoolid") final int schoolId) {
        Specification<Location> specification = new Specification<Location>() {
            @Override
            public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate condition = criteriaBuilder.equal(root.get("school_id"), schoolId);
                return criteriaBuilder.and(condition);
            }
        };
        return (Location) baseRepository.findOne(specification);
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
//        return baseRepository.findOne(specification);
//    }
}
