/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.serviceexchangeentities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Nouran
 */
@Entity
@Table(name = "user_badge")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "UserBadge.findAll", query = "SELECT u FROM UserBadge u")
        , @NamedQuery(name = "UserBadge.findByUserId", query = "SELECT u FROM UserBadge u WHERE u.userBadgePK.userId = :userId")
        , @NamedQuery(name = "UserBadge.findByBadgeId", query = "SELECT u FROM UserBadge u WHERE u.userBadgePK.badgeId = :badgeId")
        , @NamedQuery(name = "UserBadge.findByProgress", query = "SELECT u FROM UserBadge u WHERE u.progress = :progress")})
public class UserBadge implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserBadgePK userBadgePK;
    @Column(name = "progress")
    private Integer progress;
    @JoinColumn(name = "badge_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Badge badge;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public UserBadge() {
    }

    public UserBadge(UserBadgePK userBadgePK) {
        this.userBadgePK = userBadgePK;
    }

    public UserBadge(int userId, int badgeId) {
        this.userBadgePK = new UserBadgePK(userId, badgeId);
    }

    public UserBadgePK getUserBadgePK() {
        return userBadgePK;
    }

    public void setUserBadgePK(UserBadgePK userBadgePK) {
        this.userBadgePK = userBadgePK;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
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
        hash += (userBadgePK != null ? userBadgePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserBadge)) {
            return false;
        }
        UserBadge other = (UserBadge) object;
        if ((this.userBadgePK == null && other.userBadgePK != null) || (this.userBadgePK != null && !this.userBadgePK.equals(other.userBadgePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.UserBadge[ userBadgePK=" + userBadgePK + " ]";
    }

}
