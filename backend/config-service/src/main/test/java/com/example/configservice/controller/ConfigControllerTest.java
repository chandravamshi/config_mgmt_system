package com.example.configservice.controller;

import com.example.configservice.model.Config;
import com.example.configservice.service.ConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConfigController.class)
class ConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigService configService;

    @Test
    void testGetAllConfigs() throws Exception {
        Config config1 = new Config();
        config1.setConfigKey("key1");
        config1.setValue("value1");

        Config config2 = new Config();
        config2.setConfigKey("key2");
        config2.setValue("value2");

        List<Config> configs = Arrays.asList(config1, config2);

        when(configService.getAllConfigs()).thenReturn(configs);

        mockMvc.perform(get("/api/configs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].configkey").value("key1"))
                .andExpect(jsonPath("$[1].configkey").value("key2"));
    }

    @Test
    void testCreateConfig() throws Exception {
        Config config = new Config();
        config.setConfigKey("newKey");
        config.setValue("newValue");

        when(configService.createConfig(any(Config.class))).thenReturn(config);

        mockMvc.perform(post("/api/configs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"configkey\": \"newKey\", \"value\": \"newValue\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.configkey").value("newKey"));
    }

    @Test
    void testGetConfigById() throws Exception {
        Config config = new Config();
        config.setId(1L);
        config.setConfigKey("key1");
        config.setValue("value1");

        when(configService.findById(1L)).thenReturn(Optional.of(config));

        mockMvc.perform(get("/api/configs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.configkey").value("key1"));
    }

    @Test
    void testDeleteConfig() throws Exception {
        when(configService.findById(1L)).thenReturn(Optional.of(new Config()));

        mockMvc.perform(delete("/api/configs/1"))
                .andExpect(status().isNoContent());

        verify(configService, times(1)).deleteById(1L);
    }
}
