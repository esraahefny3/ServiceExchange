/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Altysh
 */
@Entity
@Table(name = "user_email")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEmail.findAll", query = "SELECT u FROM UserEmail u")
    , @NamedQuery(name = "UserEmail.findByUserId", query = "SELECT u FROM UserEmail u WHERE u.userEmailPK.userId = :userId")
    , @NamedQuery(name = "UserEmail.findByEmail", query = "SELECT u FROM UserEmail u WHERE u.userEmailPK.email = :email")})
public class UserEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserEmailPK userEmailPK;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public UserEmail() {
    }

    public UserEmail(UserEmailPK userEmailPK) {
        this.userEmailPK = userEmailPK;
    }

    public UserEmail(int userId, String email) {
        this.userEmailPK = new UserEmailPK(userId, email);
    }

    public UserEmailPK getUserEmailPK() {
        return userEmailPK;
    }

    public void setUserEmailPK(UserEmailPK userEmailPK) {
        this.userEmailPK = userEmailPK;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEmailPK != null ? userEmailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEmail)) {
            return false;
        }
        UserEmail other = (UserEmail) object;
        if ((this.userEmailPK == null && other.userEmailPK != null) || (this.userEmailPK != null && !this.userEmailPK.equals(other.userEmailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.altysh.mavenproject1.UserEmail[ userEmailPK=" + userEmailPK + " ]";
    }
    
}
