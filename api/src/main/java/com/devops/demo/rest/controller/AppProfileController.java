package com.devops.demo.rest.controller;

import com.devops.demo.common.AppConstant;
import com.devops.demo.entity.AppProfile;
import com.devops.demo.entity.dto.AppProfileDTO;
import com.devops.demo.common.MessageKey;
import com.devops.demo.entity.Mess;
import com.devops.demo.service.AppProfileService;
import com.devops.demo.service.MessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 4:49 PM
 */
@RestController
@CrossOrigin
public class AppProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppProfileController.class);
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.datasource.url}")
    private String hostDB;
    @Value("${spring.datasource.username}")
    private String usernameDB;
    @Value("${spring.datasource.password}")
    private String passDB;
    @Value("${spring.profiles.active}")
    private String environment;
    @Value("${server.port}")
    private Long serverPort;


    @Autowired
    private AppProfileService appProfileService;
    @Autowired
    private MessService messService;

    @GetMapping(AppConstant.LIST)
    public List<AppProfileDTO> list() {
//        return null; // case faile
        return appProfileService.getAll();
    }

    @PostMapping(AppConstant.ADD)
    public ResponseEntity<String> addNew() {
        appProfileService.addNew(new AppProfile(appName, serverPort, hostDB, usernameDB, passDB, environment, new Mess(environment + " Deployed Successful !")));
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @GetMapping(AppConstant.GET)
    public AppProfileDTO findByEvm(@RequestParam("environment") String env) {
        return appProfileService.getByEnvironment(env);
    }

    @DeleteMapping(AppConstant.DELETE)
    public ResponseEntity<Void> delete(@RequestParam("environment") String environment) {
        LOGGER.info("DELETE ENVIRONMENT WITH ENVIRONMENT " + environment);

        AppProfile appProfile = appProfileService.getApp(environment);
        if (appProfile == null) {
            LOGGER.info("Unable to delete.  with environment name {} not found", environment);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        appProfileService.deleteByApp(environment);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(AppConstant.UPDATE)
    public ResponseEntity<Void> updateMessage(@RequestBody Mess mess) {
        if (messService.findByIdMess(mess) == null) {
            LOGGER.info(MessageKey.NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        messService.update(mess);
        LOGGER.info(MessageKey.DONE);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/evmactive")
    public ResponseEntity<String> getEvmActive() {
        return new ResponseEntity<String>(environment, HttpStatus.OK);
//        return new  ResponseEntity<String>(environment,HttpStatus.CONFLICT); //case fail
    }


}
