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
 * @author Nouran
 */
@Entity
@Table(name = "education")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e")
        , @NamedQuery(name = "Education.findById", query = "SELECT e FROM Education e WHERE e.educationPK.id = :id")
        , @NamedQuery(name = "Education.findByUserId", query = "SELECT e FROM Education e WHERE e.educationPK.userId = :userId")
        , @NamedQuery(name = "Education.findByDegree", query = "SELECT e FROM Education e WHERE e.degree = :degree")
        , @NamedQuery(name = "Education.findByMajor", query = "SELECT e FROM Education e WHERE e.major = :major")
        , @NamedQuery(name = "Education.findByGrade", query = "SELECT e FROM Education e WHERE e.grade = :grade")
        , @NamedQuery(name = "Education.findByStartDate", query = "SELECT e FROM Education e WHERE e.startDate = :startDate")
        , @NamedQuery(name = "Education.findByEndDate", query = "SELECT e FROM Education e WHERE e.endDate = :endDate")})
public class Education implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EducationPK educationPK;
    @Column(name = "degree")
    private String degree;
    @Column(name = "major")
    private String major;
    @Column(name = "grade")
    private String grade;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public Education() {
    }

    public Education(EducationPK educationPK) {
        this.educationPK = educationPK;
    }

    public Education(int id, int userId) {
        this.educationPK = new EducationPK(id, userId);
    }

    public EducationPK getEducationPK() {
        return educationPK;
    }

    public void setEducationPK(EducationPK educationPK) {
        this.educationPK = educationPK;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (educationPK != null ? educationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        if ((this.educationPK == null && other.educationPK != null) || (this.educationPK != null && !this.educationPK.equals(other.educationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.serviceexchange.Education[ educationPK=" + educationPK + " ]";
    }

}
