/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.utal;

import com.service_exchange.entities.Clonable;
import java.util.LinkedList;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 *
 * @author Altysh
 */
public class PageToListConverter {
    public static <T extends Clonable> List<T> convertList(Page<T> list){
        List<T> newList = new LinkedList<>();
        
        list.forEach(e->newList.add((T)e.clone()));
        System.out.println(newList);
       // System.out.println(newList);
        return newList;
    }
}
