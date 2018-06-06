/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;



import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Nouran
 */
@Entity
@Table(name = "badge")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Badge.findAll", query = "SELECT b FROM Badge b")
        , @NamedQuery(name = "Badge.findById", query = "SELECT b FROM Badge b WHERE b.id = :id")
        , @NamedQuery(name = "Badge.findByName", query = "SELECT b FROM Badge b WHERE b.name = :name")
        , @NamedQuery(name = "Badge.findByImage", query = "SELECT b FROM Badge b WHERE b.image = :image")
        , @NamedQuery(name = "Badge.findByDescription", query = "SELECT b FROM Badge b WHERE b.description = :description")
        , @NamedQuery(name = "Badge.findByTimeNeeded", query = "SELECT b FROM Badge b WHERE b.timeNeeded = :timeNeeded")
        , @NamedQuery(name = "Badge.findByType", query = "SELECT b FROM Badge b WHERE b.type = :type")
        , @NamedQuery(name = "Badge.findByIsDeleted", query = "SELECT b FROM Badge b WHERE b.isDeleted = :isDeleted")})
public class Badge implements Serializable {

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
    @Column(name = "description")
    private String description;
    @Column(name = "time_needed")
    private String timeNeeded;
    @Column(name = "type")
    private String type;
    @Column(name = "is_deleted")
    private Short isDeleted;
    @JoinColumn(name = "added_by", referencedColumnName = "email")
    @ManyToOne
    private AdminTable addedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "badge")
    private Collection<UserBadge> userBadgeCollection;

    public Badge() {
    }

    public Badge(Integer id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(String timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    public AdminTable getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(AdminTable addedBy) {
        this.addedBy = addedBy;
    }

    @XmlTransient
    public Collection<UserBadge> getUserBadgeCollection() {
        return userBadgeCollection;
    }

    public void setUserBadgeCollection(Collection<UserBadge> userBadgeCollection) {
        this.userBadgeCollection = userBadgeCollection;
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
        if (!(object instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.Badge[ id=" + id + " ]";
    }

}
