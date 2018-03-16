package org.broadleafoverrides.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgegap.web.controller.BridgeGapRegisterController;

@Configuration
public class BroadleafOverrides {
	
	@Bean
    public BridgeGapRegisterController blRegistrationService() {
        return new BridgeGapRegisterController();
    }

}
