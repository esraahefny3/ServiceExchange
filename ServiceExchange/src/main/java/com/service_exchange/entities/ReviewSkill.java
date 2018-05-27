/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esraa
 */
@Entity
@Table(name = "review_skill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReviewSkill.findAll", query = "SELECT r FROM ReviewSkill r")
    , @NamedQuery(name = "ReviewSkill.findByReviewId", query = "SELECT r FROM ReviewSkill r WHERE r.reviewSkillPK.reviewId = :reviewId")
    , @NamedQuery(name = "ReviewSkill.findBySkillId", query = "SELECT r FROM ReviewSkill r WHERE r.reviewSkillPK.skillId = :skillId")
    , @NamedQuery(name = "ReviewSkill.findByRating", query = "SELECT r FROM ReviewSkill r WHERE r.rating = :rating")})
public class ReviewSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewSkillPK reviewSkillPK;
    @Column(name = "rating")
    private Integer rating;
    @JoinColumn(name = "review_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Review review;
    @JoinColumn(name = "skill_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Skill skill;

    public ReviewSkill() {
    }

    public ReviewSkill(ReviewSkillPK reviewSkillPK) {
        this.reviewSkillPK = reviewSkillPK;
    }

    public ReviewSkill(int reviewId, int skillId) {
        this.reviewSkillPK = new ReviewSkillPK(reviewId, skillId);
    }

    public ReviewSkillPK getReviewSkillPK() {
        return reviewSkillPK;
    }

    public void setReviewSkillPK(ReviewSkillPK reviewSkillPK) {
        this.reviewSkillPK = reviewSkillPK;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewSkillPK != null ? reviewSkillPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewSkill)) {
            return false;
        }
        ReviewSkill other = (ReviewSkill) object;
        if ((this.reviewSkillPK == null && other.reviewSkillPK != null) || (this.reviewSkillPK != null && !this.reviewSkillPK.equals(other.reviewSkillPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.ReviewSkill[ reviewSkillPK=" + reviewSkillPK + " ]";
    }
    
}
