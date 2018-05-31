/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.appconfigration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author esraa
 */
 @Configuration
public class AppConfigration {
    
   @Bean
   @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
     public static ModelMapper modelMapper() {
      return new ModelMapper();
  }

}
