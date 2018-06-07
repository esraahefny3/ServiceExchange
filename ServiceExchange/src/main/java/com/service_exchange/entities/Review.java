/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "review")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
        , @NamedQuery(name = "Review.findById", query = "SELECT r FROM Review r WHERE r.id = :id")
        , @NamedQuery(name = "Review.findByComment", query = "SELECT r FROM Review r WHERE r.comment = :comment")
        , @NamedQuery(name = "Review.findByRating", query = "SELECT r FROM Review r WHERE r.rating = :rating")
//        , @NamedQuery(name = "Review.findByTransactionId", query = "SELECT r FROM Review r WHERE r.transactionId = :transactionId")
        , @NamedQuery(name = "Review.findByIsDeleted", query = "SELECT r FROM Review r WHERE r.isDeleted = :isDeleted")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "rating")
    private Integer rating;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne
    private TransactionInfo transactionId;
    @Column(name = "is_deleted")
    private Short isDeleted;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
    private Collection<ReviewSkill> reviewSkillCollection;
    @JoinColumn(name = "made_by", referencedColumnName = "id")
    @ManyToOne
    private UserTable madeBy;

    public Review() {
    }

    public Review(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public TransactionInfo getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionInfo transactionId) {
        this.transactionId = transactionId;
    }

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    public Collection<ReviewSkill> getReviewSkillCollection() {
        return reviewSkillCollection;
    }

    public void setReviewSkillCollection(Collection<ReviewSkill> reviewSkillCollection) {
        this.reviewSkillCollection = reviewSkillCollection;
    }

    public UserTable getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(UserTable madeBy) {
        this.madeBy = madeBy;
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
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.Review[ id=" + id + " ]";
    }

}
