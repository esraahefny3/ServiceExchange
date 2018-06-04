/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.api_services.dao.notification;

import java.util.Date;

/**
 * @author Nouran
 */
public class NotificationDto {

    private Integer id;
    private String body;
    private Date notifecationDate;
    private String sentBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getNotifecationDate() {
        return notifecationDate;
    }

    public void setNotifecationDate(Date notifecationDate) {
        this.notifecationDate = notifecationDate;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

}
