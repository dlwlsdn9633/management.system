package com.service.management.system.config;

import com.service.management.system.converters.CommentRequestDtoToComment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@RequiredArgsConstructor
public class ConversionConfig {
    @Bean
    public DefaultConversionService defaultConversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new CommentRequestDtoToComment());
        return conversionService;
    }
}
