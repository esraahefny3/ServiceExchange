/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author Nouran
 */
@Embeddable
public class UserChallengePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "challenge_id")
    private int challengeId;

    public UserChallengePK() {
    }

    public UserChallengePK(int userId, int challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) challengeId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserChallengePK)) {
            return false;
        }
        UserChallengePK other = (UserChallengePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.challengeId != other.challengeId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.UserChallengePK[ userId=" + userId + ", challengeId=" + challengeId + " ]";
    }

}
