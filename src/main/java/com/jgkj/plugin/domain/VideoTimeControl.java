package com.jgkj.plugin.domain;

import javax.persistence.*;

/**
 * Created by chen on 16/10/20.
 */
@Entity
@Table(name="plugin_video_time_control")
public class VideoTimeControl {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int school_id;

    @Column(nullable = false)
    private int class_id;

    @Column(nullable = false)
    private String start_time;

    @Column(nullable = false)
    private String end_time;

    @Column(nullable = false)
    private int is_allow_play;  //0 允许播放  ， 1不允许播放

    @Column(nullable = false)
    private int serial_number;

    @Column(nullable = false)
    private String verify_number;

    public String getVerify_number() {
        return verify_number;
    }

    public void setVerify_number(String verify_number) {
        this.verify_number = verify_number;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getIs_allow_play() {
        return is_allow_play;
    }

    public void setIs_allow_play(int is_allow_play) {
        this.is_allow_play = is_allow_play;
    }
}
