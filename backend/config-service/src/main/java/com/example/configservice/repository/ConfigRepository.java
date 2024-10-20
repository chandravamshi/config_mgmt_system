package com.example.configservice.repository;

import com.example.configservice.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Long> {
    Config findByKey(String key);
}
