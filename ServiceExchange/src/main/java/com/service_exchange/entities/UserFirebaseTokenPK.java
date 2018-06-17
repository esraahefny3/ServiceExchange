package com.service_exchange.entities;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author esraa
 */
@Embeddable
public class UserFirebaseTokenPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;

    public UserFirebaseTokenPK() {
    }

    public UserFirebaseTokenPK(int userId, String token, String type) {
        this.userId = userId;
        this.token = token;
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (token != null ? token.hashCode() : 0);
        hash += (type != null ? type.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserFirebaseTokenPK)) {
            return false;
        }
        UserFirebaseTokenPK other = (UserFirebaseTokenPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        if ((this.type == null && other.type != null) || (this.type != null && !this.type.equals(other.type))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esraa.spring.mavenproject1.UserFirebaseTokenPK[ userId=" + userId + ", token=" + token + ", type=" + type + " ]";
    }
    
}
