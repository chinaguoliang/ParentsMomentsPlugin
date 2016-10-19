package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 16/10/19.
 */
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
    public List<Location> getSchoolLocation() {
        List<Location> locationList = baseRepository.findAll();
        return locationList;
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
}
