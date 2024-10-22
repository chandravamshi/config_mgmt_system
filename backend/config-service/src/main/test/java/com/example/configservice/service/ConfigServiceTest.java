package com.example.configservice.service;

import com.example.configservice.model.Config;
import com.example.configservice.repository.ConfigRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigServiceTest {

    @Mock
    private ConfigRepository configRepository;

    @InjectMocks
    private ConfigService configService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConfig() {
        Config config = new Config();
        config.setConfigKey("testKey");
        config.setValue("testValue");

        when(configRepository.save(config)).thenReturn(config);

        Config createdConfig = configService.createConfig(config);
        assertNotNull(createdConfig);
        assertEquals("testKey", createdConfig.getConfigKey());
        verify(configRepository, times(1)).save(config);
    }

    @Test
    void testFindById() {
        Config config = new Config();
        config.setId(1L);
        when(configRepository.findById(1L)).thenReturn(Optional.of(config));

        Optional<Config> foundConfig = configService.findById(1L);
        assertTrue(foundConfig.isPresent());
        assertEquals(1L, foundConfig.get().getId());
    }

    @Test
    void testDeleteById() {
        configService.deleteById(1L);
        verify(configRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateConfigById() {
        Config existingConfig = new Config();
        existingConfig.setId(1L);
        existingConfig.setConfigKey("oldKey");
        existingConfig.setValue("oldValue");

        Config updatedConfig = new Config();
        updatedConfig.setConfigKey("newKey");
        updatedConfig.setValue("newValue");

        when(configRepository.findById(1L)).thenReturn(Optional.of(existingConfig));
        when(configRepository.save(existingConfig)).thenReturn(existingConfig);

        Config result = configService.updateConfigById(1L, updatedConfig);

        assertEquals("newKey", result.getConfigKey());
        assertEquals("newValue", result.getValue());
        verify(configRepository, times(1)).save(existingConfig);
    }
}
