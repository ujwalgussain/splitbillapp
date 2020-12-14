package com.app.splitbillapp.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyConfig {
    @Bean
    fun getObjectMapper() : ObjectMapper
    {
        return ObjectMapper()
    }
}