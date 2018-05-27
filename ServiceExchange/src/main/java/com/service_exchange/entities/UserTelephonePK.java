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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author esraa
 */
@Embeddable
public class UserTelephonePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telephone")
    private String telephone;

    public UserTelephonePK() {
    }

    public UserTelephonePK(int userId, String telephone) {
        this.userId = userId;
        this.telephone = telephone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (telephone != null ? telephone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTelephonePK)) {
            return false;
        }
        UserTelephonePK other = (UserTelephonePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.telephone == null && other.telephone != null) || (this.telephone != null && !this.telephone.equals(other.telephone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.UserTelephonePK[ userId=" + userId + ", telephone=" + telephone + " ]";
    }
    
}
