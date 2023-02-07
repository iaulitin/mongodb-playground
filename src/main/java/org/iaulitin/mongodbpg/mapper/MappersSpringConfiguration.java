package org.iaulitin.mongodbpg.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersSpringConfiguration {

    @Bean
    public CustomerMapper customerMapper() {
        return CustomerMapper.INSTANCE;
    }
}
