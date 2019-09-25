package com.devops.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 4:57 PM
 */
@Entity
public class AppProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appName;

    private Long portRun;

    private String hostDb;

    private String userDb;

    private String passDb;

    private String environment;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "mess_id")
    private Mess mess;

    public AppProfile() {
    }

    public AppProfile(String appName, Long portRun, String hostDb, String userDb, String passDb, String environment,Mess mess) {
        this.appName = appName;
        this.portRun = portRun;
        this.hostDb = hostDb;
        this.userDb = userDb;
        this.passDb = passDb;
        this.environment = environment;
        this.mess = mess;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getPortRun() {
        return portRun;
    }

    public void setPortRun(Long portRun) {
        this.portRun = portRun;
    }

    public String getHostDb() {
        return hostDb;
    }

    public void setHostDb(String hostDb) {
        this.hostDb = hostDb;
    }

    public String getUserDb() {
        return userDb;
    }

    public void setUserDb(String userDb) {
        this.userDb = userDb;
    }

    public String getPassDb() {
        return passDb;
    }

    public void setPassDb(String passDb) {
        this.passDb = passDb;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
        this.mess = mess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppProfile)) return false;
        AppProfile that = (AppProfile) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAppName(), that.getAppName()) &&
                Objects.equals(getPortRun(), that.getPortRun()) &&
                Objects.equals(getHostDb(), that.getHostDb()) &&
                Objects.equals(getUserDb(), that.getUserDb()) &&
                Objects.equals(getPassDb(), that.getPassDb()) &&
                Objects.equals(getEnvironment(), that.getEnvironment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAppName(), getPortRun(), getHostDb(), getUserDb(), getPassDb(), getEnvironment());
    }
}
