package com.mts.user.config;

import com.mts.user.service.UserRestServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserRestServiceImpl.class);
    }
}
