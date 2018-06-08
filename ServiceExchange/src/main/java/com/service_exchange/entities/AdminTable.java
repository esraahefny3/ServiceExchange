/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Nouran
 */
@Entity
@Table(name = "admin_table")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AdminTable.findAll", query = "SELECT a FROM AdminTable a")
        , @NamedQuery(name = "AdminTable.findByEmail", query = "SELECT a FROM AdminTable a WHERE a.email = :email")
        , @NamedQuery(name = "AdminTable.findByName", query = "SELECT a FROM AdminTable a WHERE a.name = :name")
        , @NamedQuery(name = "AdminTable.findByPassword", query = "SELECT a FROM AdminTable a WHERE a.password = :password")
        , @NamedQuery(name = "AdminTable.findByImage", query = "SELECT a FROM AdminTable a WHERE a.image = :image")
        , @NamedQuery(name = "AdminTable.findByIsEnabled", query = "SELECT a FROM AdminTable a WHERE a.isEnabled = :isEnabled")})
public class AdminTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "image")
    private String image;
    @Column(name = "last_password_changed")
    private Date lastPasswordChanged;
    @Column(name = "is_enabled")
    private Short isEnabled;
    @OneToMany(mappedBy = "addedBy")
    private Collection<Badge> badgeCollection;
    @OneToMany(mappedBy = "sentBy")
    private Collection<Notification> notificationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminTable")
    private Collection<AdminAuthority> adminAuthorityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminTable")
    private Collection<AdminTelephone> adminTelephoneCollection;
    @OneToMany(mappedBy = "reviewedBy")
    private Collection<Complaint> complaintCollection;
    @OneToMany(mappedBy = "addedBy")
    private Collection<Challenge> challengeCollection;

    public AdminTable() {
    }

    public AdminTable(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Short getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Short isEnabled) {
        this.isEnabled = isEnabled;
    }

    @XmlTransient
    public Collection<Badge> getBadgeCollection() {
        return badgeCollection;
    }

    public void setBadgeCollection(Collection<Badge> badgeCollection) {
        this.badgeCollection = badgeCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @XmlTransient
    public Collection<AdminAuthority> getAdminAuthorityCollection() {
        return adminAuthorityCollection;
    }

    public void setAdminAuthorityCollection(Collection<AdminAuthority> adminAuthorityCollection) {
        this.adminAuthorityCollection = adminAuthorityCollection;
    }

    @XmlTransient
    public Collection<AdminTelephone> getAdminTelephoneCollection() {
        return adminTelephoneCollection;
    }

    public void setAdminTelephoneCollection(Collection<AdminTelephone> adminTelephoneCollection) {
        this.adminTelephoneCollection = adminTelephoneCollection;
    }

    @XmlTransient
    public Collection<Complaint> getComplaintCollection() {
        return complaintCollection;
    }

    public void setComplaintCollection(Collection<Complaint> complaintCollection) {
        this.complaintCollection = complaintCollection;
    }

    @XmlTransient
    public Collection<Challenge> getChallengeCollection() {
        return challengeCollection;
    }

    public void setChallengeCollection(Collection<Challenge> challengeCollection) {
        this.challengeCollection = challengeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminTable)) {
            return false;
        }
        AdminTable other = (AdminTable) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    public Date getLastPasswordChanged() {
        return lastPasswordChanged;
    }

    public void setLastPasswordChanged(Date lastPasswordChanged) {
        this.lastPasswordChanged = lastPasswordChanged;
    }

    @Override
    public String toString() {
        return "com.service_exchange.AdminTable[ email=" + email + " ]";
    }

}
