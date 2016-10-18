package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.Location;
import com.jgkj.plugin.services.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chen on 16/10/19.
 */
@Controller
public class LocationController {
    @Autowired
    LocationMapper locationMapper;

    @RequestMapping(value = "/school/position",method= RequestMethod.POST)
    @ResponseBody
    public Location getSchoolLocation(@RequestParam("schoolId") String schoolId) {
        Location location = locationMapper.findUserBySchoolId(schoolId);
        return location;
    }
}
