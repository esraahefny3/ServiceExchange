/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Altysh
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
    , @NamedQuery(name = "UserTable.findByAccountType", query = "SELECT u FROM UserTable u WHERE u.accountType = :accountType")})
public class UserTable implements Serializable,Clonable<UserTable> {

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
    private String birthDate;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "account_type")
    private String accountType;
    @ManyToMany(mappedBy = "userTableCollection")
    private Collection<Skill> skillCollection;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserBadge> userBadgeCollection;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
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

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
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
    public Collection<UserBadge> getUserBadgeCollection() {
        return userBadgeCollection;
    }

    public void setUserBadgeCollection(Collection<UserBadge> userBadgeCollection) {
        this.userBadgeCollection = userBadgeCollection;
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
        return "com.altysh.mavenproject1.UserTable[ id=" + id + " ]";
    }
        
    public Boolean addChallange(Integer ch) {
        UserChallenge uc = new UserChallenge(id, ch);
        if (!userChallengeCollection.contains(uc)) {
            userChallengeCollection.add(uc);
            return true;
        }else{
            return false;
        }
//return Boolean.FALSE;
    }
     public Boolean removeChallange(Integer ch) {
        UserChallenge uc = new UserChallenge(id, ch);
        if (userChallengeCollection.contains(uc)) {
            userChallengeCollection.remove(uc);
            return true;
        }else{
            return false;
        }
//return Boolean.FALSE;
    }

    @Override
    public UserTable clone() {
        UserTable user = new UserTable();
        user.setAccountId(accountId);
        user.setAccountType(accountType);
        user.setBirthDate(birthDate);
        user.setComplaintCollection(complaintCollection);
        user.setEducationCollection(educationCollection);
        user.setId(id);
        user.setImage(image);
        user.setMessageCollection(messageCollection);
        user.setMessageCollection1(messageCollection1);
        user.setName(name);
        user.setUserNotificationCollection(userNotificationCollection);
        user.setReviewCollection(reviewCollection);
        user.setServiceCollection(serviceCollection);
        user.setSkillCollection(skillCollection);
        user.setStatus(status);
        user.setTransactionInfoCollection(transactionInfoCollection);
        user.setUserAuthority(userAuthority);
      user.setUserBadgeCollection(userBadgeCollection);
        user.setUserChallengeCollection(userChallengeCollection);
        user.setUserEmailCollection(userEmailCollection);
        user.setUserTelephoneCollection(userTelephoneCollection);
        return user;
    }

    private <T> Collection<T> copyArray(Collection<T> list) {

        Collection<T> newList = new LinkedList<>();
        if (list != null) {
            list.forEach(e -> newList.add(e));
        }

        return newList;
    }
}
