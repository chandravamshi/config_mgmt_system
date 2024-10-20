package com.example.configservice.service;

import com.example.configservice.model.Config;
import com.example.configservice.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;


@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    public Config createConfig(@Valid Config config) {
        return configRepository.save(config);
    }

    // Method to find config by ID
    public Optional<Config> findById(Long id) {
        return configRepository.findById(id);
    }

    // Delete a config by ID
    public void deleteById(Long id) {
        configRepository.deleteById(id);
    }

    // Get all configs
    public List<Config> getAllConfigs() {
        return configRepository.findAll();
    }

    /* // Method to update config
    public Config updateConfig(Long id, Config updatedConfig) throws Exception {
        // Find the existing config by ID
        Config existingConfig = findById(id)
                .orElseThrow(() -> new Exception("Config with ID " + id + " not found"));

        // Update the config properties
        existingConfig.setKey(updatedConfig.getKey());
        existingConfig.setValue(updatedConfig.getValue());
        existingConfig.setDescription(updatedConfig.getDescription());

        // Save the updated config to the repository
        return configRepository.save(existingConfig);
    } */

    // Update a config by ID
    public Config updateConfigById(Long id, Config updatedConfig) {
        Optional<Config> existingConfigOpt = configRepository.findById(id);
        if (existingConfigOpt.isPresent()) {
            Config existingConfig = existingConfigOpt.get();
            existingConfig.setKey(updatedConfig.getKey());
            existingConfig.setValue(updatedConfig.getValue());
            existingConfig.setDescription(updatedConfig.getDescription());
            return configRepository.save(existingConfig);
        } else {
            throw new RuntimeException("Config not found with id: " + id);
        }
    }
}
