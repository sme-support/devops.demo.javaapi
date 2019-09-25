package com.devops.demo.service.iplm;

import com.devops.demo.entity.AppProfile;
import com.devops.demo.entity.dto.AppProfileDTO;
import com.devops.demo.exception.BadRequestException;
import com.devops.demo.repository.AppProfileRepository;
import com.devops.demo.common.MessageKey;
import com.devops.demo.service.AppProfileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 5:08 PM
 */
@Service
public class AppProfileServiceIplm implements AppProfileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppProfileServiceIplm.class);
    @Autowired
    private AppProfileRepository appProfileRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AppProfileDTO> getAll() {
        LOGGER.info(MessageKey.DONE);
        List<AppProfile> appProfiles = appProfileRepository.findAll();
        if (appProfiles == null) {
            return null;
        }
        return appProfiles.stream().map(a -> modelMapper.map(a, AppProfileDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void addNew(AppProfile appProfile) {
        if (exists(appProfile)) {
            throw new BadRequestException(MessageKey.CONFLICT);
        }
        appProfileRepository.save(appProfile);

    }

    @Override
    public AppProfileDTO  getByEnvironment(String environment) {
        AppProfile appProfile = appProfileRepository.findByEnvironment(environment);
        if (appProfile == null) {
            throw new BadRequestException(MessageKey.NOT_FOUND);
        }
        return modelMapper.map(appProfile, AppProfileDTO.class);

    }

    @Override
    public void deleteByApp(String environment) {
        AppProfile appProfile = appProfileRepository.findByEnvironment(environment);
        appProfileRepository.delete(appProfile);
    }

    @Override
    public boolean exists(AppProfile appProfile) {
        if (appProfileRepository.findByEnvironment(appProfile.getEnvironment()) != null) {
            LOGGER.info(appProfile.getEnvironment() + "  " + MessageKey.IS_EXISTED);
            return true;
        }
        return false;
    }

    @Override
    public AppProfile getApp(String environment) {
        if (environment == null) {
            return null;
        }
        if (appProfileRepository.findByEnvironment(environment) != null) {
            return appProfileRepository.findByEnvironment(environment);
        }
        return null;
    }
}
