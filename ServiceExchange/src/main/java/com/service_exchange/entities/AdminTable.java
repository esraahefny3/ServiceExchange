/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Altysh
 */
@Entity
@Table(name = "admin_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminTable.findAll", query = "SELECT a FROM AdminTable a")
    , @NamedQuery(name = "AdminTable.findByEmail", query = "SELECT a FROM AdminTable a WHERE a.email = :email")
    , @NamedQuery(name = "AdminTable.findByName", query = "SELECT a FROM AdminTable a WHERE a.name = :name")
    , @NamedQuery(name = "AdminTable.findByPassword", query = "SELECT a FROM AdminTable a WHERE a.password = :password")
    , @NamedQuery(name = "AdminTable.findByImage", query = "SELECT a FROM AdminTable a WHERE a.image = :image")})
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

    @Override
    public String toString() {
        return "com.altysh.mavenproject1.AdminTable[ email=" + email + " ]";
    }
    
}
