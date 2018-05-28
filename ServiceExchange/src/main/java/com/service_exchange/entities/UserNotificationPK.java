/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Altysh
 */
@Embeddable
public class UserNotificationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "notification_id")
    private int notificationId;

    public UserNotificationPK() {
    }

    public UserNotificationPK(int userId, int notificationId) {
        this.userId = userId;
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) notificationId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserNotificationPK)) {
            return false;
        }
        UserNotificationPK other = (UserNotificationPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.notificationId != other.notificationId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.altysh.mavenproject1.UserNotificationPK[ userId=" + userId + ", notificationId=" + notificationId + " ]";
    }
    
}
