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
 *
 * @author Nouran
 */
@Entity
@Table(name = "service")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
        , @NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id")
        , @NamedQuery(name = "Service.findByName", query = "SELECT s FROM Service s WHERE s.name = :name")
        , @NamedQuery(name = "Service.findByImage", query = "SELECT s FROM Service s WHERE s.image = :image")
        , @NamedQuery(name = "Service.findByPrice", query = "SELECT s FROM Service s WHERE s.price = :price")
        , @NamedQuery(name = "Service.findByType", query = "SELECT s FROM Service s WHERE s.type = :type")
        , @NamedQuery(name = "Service.findByDescription", query = "SELECT s FROM Service s WHERE s.description = :description")
        , @NamedQuery(name = "Service.findByIsAvailable", query = "SELECT s FROM Service s WHERE s.isAvailable = :isAvailable")})
public class Service implements Serializable {

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
    @Column(name = "price")
    private Integer price;
    @Column(name = "type")
    private String type;
    @Column(name = "description")
    private String description;
    @Column(name = "is_available")
    private String isAvailable;
    @JoinTable(name = "service_skill", joinColumns = {
            @JoinColumn(name = "service_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "skill_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Skill> skillCollection;
    @JoinColumn(name = "made_by", referencedColumnName = "id")
    @ManyToOne
    private UserTable madeBy;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "service")
    private TransactionInfo transactionInfo;
    @OneToMany(mappedBy = "serviceId")
    private Collection<TransactionInfo> transactionInfoCollection;

    public Service() {
    }

    public Service(Integer id) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    @XmlTransient
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

    public UserTable getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(UserTable madeBy) {
        this.madeBy = madeBy;
    }

    public TransactionInfo getTransactionInfo() {
        return transactionInfo;
    }

    public void setTransactionInfo(TransactionInfo transactionInfo) {
        this.transactionInfo = transactionInfo;
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
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.Service[ id=" + id + " ]";
    }

}
