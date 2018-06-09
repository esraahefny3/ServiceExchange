/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

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

    @Column(name = "last_password_changed")
    private Date lastPasswordChanged;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "balance")
    private Integer balance;
    public static final short ENABELED = 0;
    @ManyToMany(mappedBy = "userTableCollection")
    @JsonIgnore
    private Collection<Skill> skillCollection;
    public static final short DISABLED = 1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    @JsonIgnore
    private Collection<Education> educationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    @JsonIgnore
    private Collection<UserTelephone> userTelephoneCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    @JsonIgnore
    private Collection<UserNotification> userNotificationCollection;
    @OneToMany(mappedBy = "receiverId")
    @JsonIgnore
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "senderId")
    @JsonIgnore
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    @JsonIgnore
    private Collection<UserChallenge> userChallengeCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userTable")
    private UserAuthority userAuthority;
    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private Collection<Complaint> complaintCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<UserBadge> userBadgeCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "madeBy")
    private Collection<Review> reviewCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "madeBy")
    private Collection<Service> serviceCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "startedBy")
    private Collection<TransactionInfo> transactionInfoCollection;
    @JsonIgnore
    @Column(name = "is_enabled")
    private Short isEnabled = 0;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<UserEmail> userEmailCollection;

    public Boolean getFrist() {
        return isFrist;
    }

    public void setFrist(Boolean frist) {
        isFrist = frist;
    }

    @Transient
    private Boolean isFrist = Boolean.TRUE;

    public UserTable() {
    }

    @Nullable
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public UserTable(Integer id) {
        this.id = id;
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Nullable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Nullable
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Nullable
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Nullable
    public Short getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Short isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Nullable
    public Date getLastPasswordChanged() {
        return lastPasswordChanged;
    }

    public void setLastPasswordChanged(Date lastPasswordChanged) {
        this.lastPasswordChanged = lastPasswordChanged;
    }

    @Nullable
    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<UserEmail> getUserEmailCollection() {
        return userEmailCollection;
    }

    public void setUserEmailCollection(Collection<UserEmail> userEmailCollection) {
        this.userEmailCollection = userEmailCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<Education> getEducationCollection() {
        return educationCollection;
    }

    public void setEducationCollection(Collection<Education> educationCollection) {
        this.educationCollection = educationCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<UserTelephone> getUserTelephoneCollection() {
        return userTelephoneCollection;
    }

    public void setUserTelephoneCollection(Collection<UserTelephone> userTelephoneCollection) {
        this.userTelephoneCollection = userTelephoneCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<UserNotification> getUserNotificationCollection() {
        return userNotificationCollection;
    }

    public void setUserNotificationCollection(Collection<UserNotification> userNotificationCollection) {
        this.userNotificationCollection = userNotificationCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @Nullable
    @XmlTransient
    public Collection<UserChallenge> getUserChallengeCollection() {
        return userChallengeCollection;
    }

    public void setUserChallengeCollection(Collection<UserChallenge> userChallengeCollection) {
        this.userChallengeCollection = userChallengeCollection;
    }

    @Nullable
    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Nullable
    @XmlTransient
    public Collection<Complaint> getComplaintCollection() {
        return complaintCollection;
    }

    public void setComplaintCollection(Collection<Complaint> complaintCollection) {
        this.complaintCollection = complaintCollection;
    }

    @Nullable
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

    @Nullable
    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @Nullable
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
        return "UserTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", skillCollection=" + skillCollection +
                ", userEmailCollection=" + userEmailCollection +
                ", educationCollection=" + educationCollection +
                ", userTelephoneCollection=" + userTelephoneCollection +
                ", userNotificationCollection=" + userNotificationCollection +
                ", messageCollection=" + messageCollection +
                ", messageCollection1=" + messageCollection1 +
                ", userChallengeCollection=" + userChallengeCollection +
                ", userAuthority=" + userAuthority +
                ", complaintCollection=" + complaintCollection +
                ", userBadgeCollection=" + userBadgeCollection +
                ", reviewCollection=" + reviewCollection +
                ", serviceCollection=" + serviceCollection +
                ", transactionInfoCollection=" + transactionInfoCollection +
                ", isFrist=" + isFrist +
                '}';
    }

    public Boolean addChallange(Integer ch) {
        UserChallenge uc = new UserChallenge(id, ch);
        if (!userChallengeCollection.contains(uc)) {
            userChallengeCollection.add(uc);
            return true;
        } else {
            return false;
        }
//return Boolean.FALSE;
    }

    public Boolean removeChallange(Integer ch) {
        UserChallenge uc = new UserChallenge(id, ch);
        if (userChallengeCollection.contains(uc)) {
            userChallengeCollection.remove(uc);
            return true;
        } else {
            return false;
        }
//return Boolean.FALSE;
    }

    public Boolean addSkill(Skill skill) {
        if (!skillCollection.contains(skill)) {
            skillCollection.add(skill);
            return true;
        }
        return false;
    }

    public Boolean addEmail(Integer userId, String email) {
        UserEmail userEmail = new UserEmail(userId, email, this);
        if (!userEmailCollection.contains(userEmail)) {
            userEmailCollection.add(userEmail);
            return true;
        }
        return false;
    }

    public Boolean RemoveEmail(String email) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        userEmailCollection.stream().filter(userEmail -> userEmail.userEmailPK.getEmail().equals(email)).
                findFirst().ifPresent(userEmail -> {
            userEmailCollection.remove(userEmail);
            bool.set(true);
        });

        return bool.get();
    }

    public Boolean addTelephone(Integer userId, String telephone) {
        UserTelephone userTelephone = new UserTelephone(userId, telephone, this);
        if (!userTelephoneCollection.contains(userTelephone)) {
            userTelephoneCollection.add(userTelephone);
            return true;
        }
        return false;
    }

    public Boolean RemoveTelephone(String telephone) {
        AtomicReference<Boolean> bool = new AtomicReference<>(false);
        userTelephoneCollection.stream().filter(userTelephone -> userTelephone.userTelephonePK.getTelephone().equals(telephone)).
                findFirst().ifPresent(userTelephone -> {
            userTelephoneCollection.remove(userTelephone);
            bool.set(true);
        });

        return bool.get();
    }

    public Boolean removeSkill(Skill skill) {
        if (skillCollection.contains(skill)) {
            skillCollection.remove(skill);
            return true;
        }
        return false;
    }

    public Boolean addEducation(Education education) {
        if (!educationCollection.contains(education)) {
            educationCollection.add(education);
            return true;
        }
        return false;
    }

    public Boolean removeEducation(Education education) {
        if (educationCollection.contains(education)) {
            educationCollection.remove(education);
            return true;
        }
        return false;
    }

    public Boolean addReview(Review review) {
        if (!reviewCollection.contains(review)) {
            reviewCollection.add(review);
            return true;
        }
        return false;
    }

    public Boolean addService(Service service) {
        if (!serviceCollection.contains(service)) {
            serviceCollection.add(service);
            return true;
        }
        return false;
    }

    public Boolean removeService(Service service) {
        if (serviceCollection.contains(service) && !service.getIsAvailable().equals(Service.DELETED)) {
            service.setIsAvailable(Service.DELETED);
            return true;
        }
        return false;
    }


    private <T> Collection<T> copyArray(Collection<T> list) {

        Collection<T> newList = new LinkedList<>();
        if (list != null) {
            list.forEach(e -> newList.add(e));
        }

        return newList;
    }

    public Boolean isEnabled() {
        if (isEnabled != null)
            return isEnabled == ENABELED;
        else return true;
    }

    //---------esraa------
    public Boolean addBadge(UserBadge userBadge) {
        if (!userBadgeCollection.contains(userBadge)) {
            userBadgeCollection.add(userBadge);
            return true;
        }
        return false;
    }


    //-------esraaa--------------
}
