/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.serviceexchangeentities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Nouran
 */
@Entity
@Table(name = "user_challenge")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UserChallenge.findAll", query = "SELECT u FROM UserChallenge u")
        , @NamedQuery(name = "UserChallenge.findByUserId", query = "SELECT u FROM UserChallenge u WHERE u.userChallengePK.userId = :userId")
        , @NamedQuery(name = "UserChallenge.findByChallengeId", query = "SELECT u FROM UserChallenge u WHERE u.userChallengePK.challengeId = :challengeId")
        , @NamedQuery(name = "UserChallenge.findByStartDate", query = "SELECT u FROM UserChallenge u WHERE u.startDate = :startDate")
        , @NamedQuery(name = "UserChallenge.findByEndDate", query = "SELECT u FROM UserChallenge u WHERE u.endDate = :endDate")
        , @NamedQuery(name = "UserChallenge.findByEnded", query = "SELECT u FROM UserChallenge u WHERE u.ended = :ended")})
public class UserChallenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserChallengePK userChallengePK;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "ended")
    private Integer ended;
    @JoinColumn(name = "challenge_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Challenge challenge;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public UserChallenge() {
    }

    public UserChallenge(UserChallengePK userChallengePK) {
        this.userChallengePK = userChallengePK;
    }

    public UserChallenge(int userId, int challengeId) {
        this.userChallengePK = new UserChallengePK(userId, challengeId);
    }

    public UserChallengePK getUserChallengePK() {
        return userChallengePK;
    }

    public void setUserChallengePK(UserChallengePK userChallengePK) {
        this.userChallengePK = userChallengePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEnded() {
        return ended;
    }

    public void setEnded(Integer ended) {
        this.ended = ended;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
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
        hash += (userChallengePK != null ? userChallengePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserChallenge)) {
            return false;
        }
        UserChallenge other = (UserChallenge) object;
        if ((this.userChallengePK == null && other.userChallengePK != null) || (this.userChallengePK != null && !this.userChallengePK.equals(other.userChallengePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.UserChallenge[ userChallengePK=" + userChallengePK + " ]";
    }

}
