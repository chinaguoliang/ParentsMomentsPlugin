package com.jgkj.plugin.controllers;

import com.jgkj.plugin.domain.MyLocation;
import com.jgkj.plugin.repositories.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by chen on 16/11/13.
 */
@Controller
public class TestMybatisController {
    @Autowired
    LocationMapper locationMap;

    @RequestMapping("/testOneLocation")
    @ResponseBody
    public MyLocation getOneLocation(@RequestParam("id") String id) {
        MyLocation location = locationMap.findOneLocation(id);
        return location;
    }


    @RequestMapping("/testAllLocation")
    @ResponseBody
    public List<MyLocation> getAllLocation() {
        List<MyLocation> location = locationMap.findAllLocation();
        return location;
    }

}
