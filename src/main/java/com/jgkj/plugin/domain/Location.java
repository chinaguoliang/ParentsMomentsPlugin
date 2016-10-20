package com.jgkj.plugin.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by chen on 16/10/19.
 */
@Entity
@Table(name="plugin_location")
public class Location implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    public int school_id;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
