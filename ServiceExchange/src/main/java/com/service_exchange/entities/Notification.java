/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Altysh
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findById", query = "SELECT n FROM Notification n WHERE n.id = :id")
    , @NamedQuery(name = "Notification.findByBody", query = "SELECT n FROM Notification n WHERE n.body = :body")
    , @NamedQuery(name = "Notification.findByNotifecationDate", query = "SELECT n FROM Notification n WHERE n.notifecationDate = :notifecationDate")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "body")
    private String body;
    @Column(name = "notifecation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date notifecationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notification")
    private Collection<UserNotification> userNotificationCollection;
    @JoinColumn(name = "sent_by", referencedColumnName = "email")
    @ManyToOne
    private AdminTable sentBy;

    public Notification() {
    }

    public Notification(Integer id) {
        this.id = id;
    }

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

    @XmlTransient
    @JsonIgnore
    public Collection<UserNotification> getUserNotificationCollection() {
        return userNotificationCollection;
    }

    public void setUserNotificationCollection(Collection<UserNotification> userNotificationCollection) {
        this.userNotificationCollection = userNotificationCollection;
    }

    public AdminTable getSentBy() {
        return sentBy;
    }

    public void setSentBy(AdminTable sentBy) {
        this.sentBy = sentBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.altysh.mavenproject1.Notification[ id=" + id + " ]";
    }

}
