package com.devops.demo;

import com.devops.demo.config.Filter;
import com.devops.demo.entity.AppProfile;
import com.devops.demo.entity.Mess;
import com.devops.demo.entity.dto.AppProfileDTO;
import com.devops.demo.rest.controller.AppProfileController;
import com.devops.demo.service.AppProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.devops.demo.service.MessService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Author: DatVM2
 * @Date: 1/27/19
 * Time: 6:36 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppProfileTest {
    private MockMvc mvc;
    @Mock
    private AppProfileService appProfileService;
    @Mock
    private MessService messService;
    @InjectMocks
    private AppProfileController appProfileController;
    @Autowired
    private ModelMapper modelMapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(appProfileController)
                .addFilter(new Filter()).build();
    }

    @Test
    public void contextLoads() {
    }

    private static final List<AppProfile> appProfiles = Arrays.asList(
            new AppProfile("demo app", 8080L, "localhost", "root", "root", "dev", new Mess("Hello everybody")),
            new AppProfile("demo app", 8081L, "localhost", "root", "root", "qa", new Mess("Hello everybody")));
    /**=======GET ALL ENVIRONMENTS========*/
    @Test
    public void test_getall() throws Exception {
        List<AppProfileDTO> appProfileDTOS = appProfiles.stream().map(a -> modelMapper.map(a, AppProfileDTO.class)).collect(Collectors.toList());
        when(appProfileService.getAll()).thenReturn(appProfileDTOS);
        mvc.perform(get("/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].portRun", is(8080)))
                .andExpect(jsonPath("$[0].environment", is("dev")))
                .andExpect(jsonPath("$[1].portRun", is(8081)))
                .andExpect(jsonPath("$[1].environment", is("qa")));
        verify(appProfileService, times(1)).getAll();
        verifyNoMoreInteractions(appProfileService);

    }

    /**=======ADD EVM SUCCESS========*/
    @Test
    public void test_create_success() throws Exception {
        AppProfile appProfile = new AppProfile();
        when(appProfileService.exists(any(AppProfile.class))).thenReturn(false);
        doNothing().when(appProfileService).addNew(any(AppProfile.class));
        mvc.perform(post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonObject(appProfile)))
                .andExpect(status().isCreated());
        verify(appProfileService, times(1)).addNew(appProfile);
        verifyNoMoreInteractions(appProfileService);
    }
    /**=======GET EVM BY NAME SUCCESS========*/
    @Test
    public void test_getByEnvironment_Success() throws Exception {
        AppProfile appProfile = new AppProfile("demo app", 8080L, "localhost", "root", "root", "DEV", new Mess("Hello everybody"));

        when(appProfileService.getByEnvironment("DEV")).thenReturn(modelMapper.map(appProfile, AppProfileDTO.class));
        mvc.perform(get("/get?environment=DEV"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.environment", is("DEV")));

        verify(appProfileService, times(1)).getByEnvironment("DEV");
        verifyNoMoreInteractions(appProfileService);
    }

    /**=======DELETE EVM SUCCESS========*/
    @Test
    public void test_delete_appprofile() throws Exception {
        AppProfile appProfile = new AppProfile("demo app", 8080L, "localhost", "root", "root", "DEV", new Mess("Hello everybody"));
        when(appProfileService.getApp(appProfile.getEnvironment())).thenReturn(appProfile);
        doNothing().when(appProfileService).deleteByApp(appProfile.getEnvironment());
        mvc.perform(delete("/delete?environment=DEV"))
                .andExpect(status().isOk());
        verify(appProfileService, times(1)).getApp(appProfile.getEnvironment());
        verify(appProfileService, times(1)).deleteByApp(appProfile.getEnvironment());
        verifyNoMoreInteractions(appProfileService);
    }
    /**=======DELETE EVM FAILED========*/
    @Test
    public void test_delete_fail_404_not_found() throws Exception {
        AppProfile appProfile = new AppProfile("demo app", 8080L, "localhost", "root", "root", "123", new Mess("Hello everybody"));

        when(appProfileService.getByEnvironment(appProfile.getEnvironment())).thenReturn(null);

        mvc.perform(
                delete("/delete?environment=123"))
                .andExpect(status().isNotFound());

        assertNull(appProfileService.getByEnvironment(appProfile.getEnvironment()));

    }

    @Test
    public void update_Message() throws Exception {
        Mess mess = new Mess();

        when(messService.findByIdMess(any(Mess.class))).thenReturn(mess);
        doNothing().when(messService).update(any(Mess.class));
        mvc.perform(put("/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonObject(mess)))
                .andExpect(status().isOk());
        verify(messService, times(1)).findByIdMess(any(Mess.class));
        verify(messService, times(1)).update(any(Mess.class));
        verifyNoMoreInteractions(messService);

    }

    @Test
    public void test_update_mess_fail() throws Exception {
        Mess mess = new Mess();
        when(messService.findByIdMess(any(Mess.class))).thenReturn(null);
        mvc.perform(put("/update").contentType(MediaType.APPLICATION_JSON_UTF8).content(asJsonObject(mess))).andExpect(status().isNotFound());
        verify(messService, times(1)).findByIdMess(any(Mess.class));
        verifyNoMoreInteractions(messService);
    }
    @Ignore
    @Test
    public void test_headers() throws Exception {
        mvc.perform(get("/list"))
                .andExpect(header().string("x-demo", "super-header"))
                .andExpect(header().string("access-control-allow-methods", "POST, GET, PUT, OPTIONS, DELETE"));
    }
    @Test
    public void test_getevm_active() throws Exception {
        mvc.perform(get("/evmactive"))
                .andExpect(status().isOk());

    }

    private String asJsonObject(Object o) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);
    }
}
