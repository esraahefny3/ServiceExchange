package com.service_exchange.entities;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author esraa
 */
@Entity
@Table(name = "user_firebase_token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserFirebaseToken.findAll", query = "SELECT u FROM UserFirebaseToken u")
    , @NamedQuery(name = "UserFirebaseToken.findByUserId", query = "SELECT u FROM UserFirebaseToken u WHERE u.userFirebaseTokenPK.userId = :userId")
    , @NamedQuery(name = "UserFirebaseToken.findByToken", query = "SELECT u FROM UserFirebaseToken u WHERE u.userFirebaseTokenPK.token = :token")
    , @NamedQuery(name = "UserFirebaseToken.findByType", query = "SELECT u FROM UserFirebaseToken u WHERE u.userFirebaseTokenPK.type = :type")})
public class UserFirebaseToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserFirebaseTokenPK userFirebaseTokenPK;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public static String androidType="android";
    public static String webType="web";

    public UserFirebaseToken() {
    }

    public UserFirebaseToken(UserFirebaseTokenPK userFirebaseTokenPK) {
        this.userFirebaseTokenPK = userFirebaseTokenPK;
    }

    public UserFirebaseToken(int userId, String token, String type) {
        this.userFirebaseTokenPK = new UserFirebaseTokenPK(userId, token, type);
    }

    @Nullable
    public UserFirebaseTokenPK getUserFirebaseTokenPK() {
        return userFirebaseTokenPK;
    }

    public void setUserFirebaseTokenPK(UserFirebaseTokenPK userFirebaseTokenPK) {
        this.userFirebaseTokenPK = userFirebaseTokenPK;
    }

    @Nullable
    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userFirebaseTokenPK != null ? userFirebaseTokenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserFirebaseToken)) {
            return false;
        }
        UserFirebaseToken other = (UserFirebaseToken) object;
        if ((this.userFirebaseTokenPK == null && other.userFirebaseTokenPK != null) || (this.userFirebaseTokenPK != null && !this.userFirebaseTokenPK.equals(other.userFirebaseTokenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esraa.spring.mavenproject1.UserFirebaseToken[ userFirebaseTokenPK=" + userFirebaseTokenPK + " ]";
    }
    
}
