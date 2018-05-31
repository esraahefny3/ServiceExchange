/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.factories;

import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author esraa
 */
@Component
public class AppFactory {
    
   private static AppFactory appFactory;
   @Autowired
    private  ModelMapper modelMapper;
   
   @PostConstruct
    public void registerInstance() {
        this.appFactory = this;
    }
   public static <T,M> M mapToDto(T t,Class<M> m)
   {
       return (M)appFactory.modelMapper.map(t, m);
   }
}
