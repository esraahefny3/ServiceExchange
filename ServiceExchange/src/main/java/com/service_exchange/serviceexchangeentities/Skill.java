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

/**
 * @author Nouran
 */
@Entity
@Table(name = "skill")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
        , @NamedQuery(name = "Skill.findById", query = "SELECT s FROM Skill s WHERE s.id = :id")
        , @NamedQuery(name = "Skill.findByName", query = "SELECT s FROM Skill s WHERE s.name = :name")
        , @NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description")
        , @NamedQuery(name = "Skill.findByIsVerified", query = "SELECT s FROM Skill s WHERE s.isVerified = :isVerified")
        , @NamedQuery(name = "Skill.findByIsDeleted", query = "SELECT s FROM Skill s WHERE s.isDeleted = :isDeleted")})
public class Skill implements Serializable {

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
    @Column(name = "is_verified")
    private Short isVerified;
    @Column(name = "is_deleted")
    private Short isDeleted;
    @ManyToMany(mappedBy = "skillCollection")
    private Collection<Service> serviceCollection;
    @JoinTable(name = "user_skill", joinColumns = {
            @JoinColumn(name = "skill_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<UserTable> userTableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skill")
    private Collection<ReviewSkill> reviewSkillCollection;
    @OneToMany(mappedBy = "parentSkillId")
    private Collection<Skill> skillCollection;
    @JoinColumn(name = "parent_skill_id", referencedColumnName = "id")
    @ManyToOne
    private Skill parentSkillId;

    public Skill() {
    }

    public Skill(Integer id) {
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

    public Short getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Short isVerified) {
        this.isVerified = isVerified;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    public Collection<Service> getServiceCollection() {
        return serviceCollection;
    }

    public void setServiceCollection(Collection<Service> serviceCollection) {
        this.serviceCollection = serviceCollection;
    }

    @XmlTransient
    public Collection<UserTable> getUserTableCollection() {
        return userTableCollection;
    }

    public void setUserTableCollection(Collection<UserTable> userTableCollection) {
        this.userTableCollection = userTableCollection;
    }

    @XmlTransient
    public Collection<ReviewSkill> getReviewSkillCollection() {
        return reviewSkillCollection;
    }

    public void setReviewSkillCollection(Collection<ReviewSkill> reviewSkillCollection) {
        this.reviewSkillCollection = reviewSkillCollection;
    }

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    public Skill getParentSkillId() {
        return parentSkillId;
    }

    public void setParentSkillId(Skill parentSkillId) {
        this.parentSkillId = parentSkillId;
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
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.serviceexchange.Skill[ id=" + id + " ]";
    }

}
