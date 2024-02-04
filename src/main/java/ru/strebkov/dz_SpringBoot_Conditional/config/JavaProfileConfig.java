package ru.strebkov.dz_SpringBoot_Conditional.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import ru.strebkov.dz_SpringBoot_Conditional.system.DevProfile;
import ru.strebkov.dz_SpringBoot_Conditional.system.ProductionProfile;
import ru.strebkov.dz_SpringBoot_Conditional.system.SystemProfile;

@Configuration
public class JavaProfileConfig {
    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
