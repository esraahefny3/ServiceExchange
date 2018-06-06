/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.serviceexchangeentities;

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
@Table(name = "user_table")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UserTable.findAll", query = "SELECT u FROM UserTable u")
        , @NamedQuery(name = "UserTable.findById", query = "SELECT u FROM UserTable u WHERE u.id = :id")
        , @NamedQuery(name = "UserTable.findByName", query = "SELECT u FROM UserTable u WHERE u.name = :name")
        , @NamedQuery(name = "UserTable.findByImage", query = "SELECT u FROM UserTable u WHERE u.image = :image")
        , @NamedQuery(name = "UserTable.findByStatus", query = "SELECT u FROM UserTable u WHERE u.status = :status")
        , @NamedQuery(name = "UserTable.findByBirthDate", query = "SELECT u FROM UserTable u WHERE u.birthDate = :birthDate")
        , @NamedQuery(name = "UserTable.findByAccountId", query = "SELECT u FROM UserTable u WHERE u.accountId = :accountId")
        , @NamedQuery(name = "UserTable.findByAccountType", query = "SELECT u FROM UserTable u WHERE u.accountType = :accountType")
        , @NamedQuery(name = "UserTable.findByIsEnabled", query = "SELECT u FROM UserTable u WHERE u.isEnabled = :isEnabled")})
public class UserTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private String status;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "is_enabled")
    private Short isEnabled;
    @ManyToMany(mappedBy = "userTableCollection")
    private Collection<Skill> skillCollection;
    @ManyToMany(mappedBy = "userTableCollection")
    private Collection<Badge> badgeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserEmail> userEmailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<Education> educationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserTelephone> userTelephoneCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserNotification> userNotificationCollection;
    @OneToMany(mappedBy = "receiverId")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "senderId")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserChallenge> userChallengeCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userTable")
    private UserAuthority userAuthority;
    @OneToMany(mappedBy = "userId")
    private Collection<Complaint> complaintCollection;
    @OneToMany(mappedBy = "madeBy")
    private Collection<Review> reviewCollection;
    @OneToMany(mappedBy = "madeBy")
    private Collection<Service> serviceCollection;
    @OneToMany(mappedBy = "startedBy")
    private Collection<TransactionInfo> transactionInfoCollection;

    public UserTable() {
    }

    public UserTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Short getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Short isEnabled) {
        this.isEnabled = isEnabled;
    }

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    @XmlTransient
    public Collection<Badge> getBadgeCollection() {
        return badgeCollection;
    }

    public void setBadgeCollection(Collection<Badge> badgeCollection) {
        this.badgeCollection = badgeCollection;
    }

    @XmlTransient
    public Collection<UserEmail> getUserEmailCollection() {
        return userEmailCollection;
    }

    public void setUserEmailCollection(Collection<UserEmail> userEmailCollection) {
        this.userEmailCollection = userEmailCollection;
    }

    @XmlTransient
    public Collection<Education> getEducationCollection() {
        return educationCollection;
    }

    public void setEducationCollection(Collection<Education> educationCollection) {
        this.educationCollection = educationCollection;
    }

    @XmlTransient
    public Collection<UserTelephone> getUserTelephoneCollection() {
        return userTelephoneCollection;
    }

    public void setUserTelephoneCollection(Collection<UserTelephone> userTelephoneCollection) {
        this.userTelephoneCollection = userTelephoneCollection;
    }

    @XmlTransient
    public Collection<UserNotification> getUserNotificationCollection() {
        return userNotificationCollection;
    }

    public void setUserNotificationCollection(Collection<UserNotification> userNotificationCollection) {
        this.userNotificationCollection = userNotificationCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<UserChallenge> getUserChallengeCollection() {
        return userChallengeCollection;
    }

    public void setUserChallengeCollection(Collection<UserChallenge> userChallengeCollection) {
        this.userChallengeCollection = userChallengeCollection;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @XmlTransient
    public Collection<Complaint> getComplaintCollection() {
        return complaintCollection;
    }

    public void setComplaintCollection(Collection<Complaint> complaintCollection) {
        this.complaintCollection = complaintCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @XmlTransient
    public Collection<TransactionInfo> getTransactionInfoCollection() {
        return transactionInfoCollection;
    }

    public void setTransactionInfoCollection(Collection<TransactionInfo> transactionInfoCollection) {
        this.transactionInfoCollection = transactionInfoCollection;
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
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.serviceexchange.UserTable[ id=" + id + " ]";
    }

}
