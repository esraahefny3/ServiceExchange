/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author esraa
 */
@Embeddable
public class ReviewSkillPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "review_id")
    private int reviewId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skill_id")
    private int skillId;

    public ReviewSkillPK() {
    }

    public ReviewSkillPK(int reviewId, int skillId) {
        this.reviewId = reviewId;
        this.skillId = skillId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) reviewId;
        hash += (int) skillId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewSkillPK)) {
            return false;
        }
        ReviewSkillPK other = (ReviewSkillPK) object;
        if (this.reviewId != other.reviewId) {
            return false;
        }
        if (this.skillId != other.skillId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.ReviewSkillPK[ reviewId=" + reviewId + ", skillId=" + skillId + " ]";
    }
    
}
