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
import java.math.BigInteger;
import java.util.Collection;

/**
 *
 * @author Nouran
 */
@Entity
@Table(name = "challenge")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Challenge.findAll", query = "SELECT c FROM Challenge c")
        , @NamedQuery(name = "Challenge.findById", query = "SELECT c FROM Challenge c WHERE c.id = :id")
        , @NamedQuery(name = "Challenge.findByName", query = "SELECT c FROM Challenge c WHERE c.name = :name")
        , @NamedQuery(name = "Challenge.findByDescription", query = "SELECT c FROM Challenge c WHERE c.description = :description")
        , @NamedQuery(name = "Challenge.findByReward", query = "SELECT c FROM Challenge c WHERE c.reward = :reward")
        , @NamedQuery(name = "Challenge.findByPeriod", query = "SELECT c FROM Challenge c WHERE c.period = :period")})
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "reward")
    private Integer reward;
    @Column(name = "period")
    private BigInteger period;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "challenge")
    private Collection<UserChallenge> userChallengeCollection;
    @JoinColumn(name = "added_by", referencedColumnName = "email")
    @ManyToOne
    private AdminTable addedBy;

    public Challenge() {
    }

    public Challenge(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public BigInteger getPeriod() {
        return period;
    }

    public void setPeriod(BigInteger period) {
        this.period = period;
    }

    @XmlTransient
    public Collection<UserChallenge> getUserChallengeCollection() {
        return userChallengeCollection;
    }

    public void setUserChallengeCollection(Collection<UserChallenge> userChallengeCollection) {
        this.userChallengeCollection = userChallengeCollection;
    }

    public AdminTable getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AdminTable addedBy) {
        this.addedBy = addedBy;
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
        if (!(object instanceof Challenge)) {
            return false;
        }
        Challenge other = (Challenge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.Challenge[ id=" + id + " ]";
    }

}
