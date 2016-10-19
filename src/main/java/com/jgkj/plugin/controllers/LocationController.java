package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.repositories.LocationRepository;
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
    LocationRepository locationRepository;

    @RequestMapping("/attendance/getLocation/schoolid={schoolid}")
    @ResponseBody
    public Location getSchoolLocation(@PathVariable("schoolId") String schoolId) {
        Location location = locationRepository.findOne(Long.parseLong(schoolId.trim()));
        return location;
    }

    @RequestMapping("/attendance/getAllLocation")
    @ResponseBody
    public List<Location> getSchoolLocation() {
        List<Location> locationList = locationRepository.findAll();
        return locationList;
    }



    @RequestMapping("/attendance/saveLocation/schoolid={schoolid}/longitude={longitude}/latitude={latitude}")
    @ResponseBody
    public Location saveSchoolLocation(@PathVariable("schoolid") int schoolId,@PathVariable("longitude") String longitude,@PathVariable("latitude") String latitude) {
        Location location = new Location();
        location.setSchool_id(schoolId);
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return locationRepository.save(location);
    }


    @RequestMapping("/testLocation")
    @ResponseBody
    public ArrayList<String> testLocation() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("1");
        result.add("2");
        result.add("3");
        result.add("4");
        return result;
    }
}
