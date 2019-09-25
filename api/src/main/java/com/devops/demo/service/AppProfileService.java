package com.devops.demo.service;

import com.devops.demo.entity.AppProfile;
import com.devops.demo.entity.dto.AppProfileDTO;

import java.util.List;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 5:06 PM
 */
public interface AppProfileService {
    List<AppProfileDTO> getAll();

    void addNew(AppProfile appProfile);

    AppProfileDTO getByEnvironment(String environment);

    void deleteByApp(String environment);

    boolean exists(AppProfile appProfile);

    AppProfile getApp(String environment);
}
