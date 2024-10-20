package com.example.configservice.controller;

import com.example.configservice.model.Config;
import com.example.configservice.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/configs")
@CrossOrigin
public class ConfigController {

    @Autowired
    private ConfigService configService;

    // Get all configs
    @GetMapping
    public ResponseEntity<List<Config>> getAllConfigs() {
        List<Config> configs = configService.getAllConfigs();
        return ResponseEntity.ok(configs);
    }

    // Update config by ID
    @PutMapping("/{id}")
    public ResponseEntity<Config> updateConfig(@PathVariable Long id, @RequestBody Config updatedConfig) {
        Config config = configService.updateConfigById(id, updatedConfig);
        return ResponseEntity.ok(config);
    }

    @PostMapping
    public ResponseEntity<Config> createConfig(@Valid @RequestBody Config config) {
        Config createdConfig = configService.createConfig(config);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdConfig);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Config> getConfigById(@PathVariable Long id) {
        return configService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConfig(@PathVariable Long id) {
        configService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
