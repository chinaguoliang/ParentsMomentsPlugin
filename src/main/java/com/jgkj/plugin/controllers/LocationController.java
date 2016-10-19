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
public class LocationController {
    @Autowired
    BaseRepository baseRepository;

    @RequestMapping("/attendance/getLocation/schoolid={schoolid}")
    @ResponseBody
    public Location getSchoolLocation(@PathVariable("schoolId") String schoolId) {
        Location location = baseRepository.findOne(Long.parseLong(schoolId.trim()));
        return location;
    }

    @RequestMapping("/attendance/getAllLocation")
    @ResponseBody
    public List<Location> getSchoolLocation() {
        List<Location> locationList = baseRepository.findAll();
        return locationList;
    }



    @RequestMapping("/attendance/saveLocation/schoolid={schoolid}/longitude={longitude}/latitude={latitude}")
    @ResponseBody
    public Location saveSchoolLocation(@PathVariable("schoolid") int schoolId,@PathVariable("longitude") String longitude,@PathVariable("latitude") String latitude) {
        Location location = new Location();
        location.setSchool_id(schoolId);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return baseRepository.save(location);
    }






    //post方式请求获取数据
    @RequestMapping(value = "/testLocation/post",method= RequestMethod.POST)
    @ResponseBody
    public String mytest(@RequestParam("schoolId") String schoolId) {
        if (schoolId == null || "".equals(schoolId)) {
            return "params is empty";
        } else {
            return schoolId;
        }
    }

    //get方式请求获取数据
    @RequestMapping(value = "/testLocation/get",method= RequestMethod.GET)
    @ResponseBody
    public String mytest1(@RequestParam("schoolId") String schoolId) {
        if (schoolId == null || "".equals(schoolId)) {
            return "params is empty";
        } else {
            return schoolId;
        }
    }
}
