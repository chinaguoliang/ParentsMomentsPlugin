package com.jgkj.plugin.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by chen on 17/1/21.
 */
public class VideoInfo implements Serializable {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    public int school_id;

    @Column(nullable = false)
    public String class_id;

    @Column(nullable = false)
    public String serial_num;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
}
