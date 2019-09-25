package com.devops.demo.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.devops.demo.entity.Mess;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppProfileDTO {
    private Long id;
    private String appName;
    private Long portRun;
    private String hostDb;
    private String environment;
    private Mess mess;

    public Mess getMess() {
        return mess;
    }

    public void setMess(Mess mess) {
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

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
