package com.devops.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 4:59 PM
 */
@Entity
public class Mess implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @OneToOne(mappedBy = "mess")
    @JsonIgnore
    private AppProfile appProfile;

    public AppProfile getAppProfile() {
        return appProfile;
    }

    public void setAppProfile(AppProfile appProfile) {
        this.appProfile = appProfile;
    }

    public Mess() {
    }


    public Mess(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
