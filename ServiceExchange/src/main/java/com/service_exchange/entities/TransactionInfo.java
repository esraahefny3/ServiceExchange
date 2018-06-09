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
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Nouran
 */
@Entity
@Table(name = "transaction_info")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "TransactionInfo.findAll", query = "SELECT t FROM TransactionInfo t")
        , @NamedQuery(name = "TransactionInfo.findById", query = "SELECT t FROM TransactionInfo t WHERE t.id = :id")
        , @NamedQuery(name = "TransactionInfo.findByType", query = "SELECT t FROM TransactionInfo t WHERE t.type = :type")
        , @NamedQuery(name = "TransactionInfo.findByState", query = "SELECT t FROM TransactionInfo t WHERE t.state = :state")
        , @NamedQuery(name = "TransactionInfo.findByStartDate", query = "SELECT t FROM TransactionInfo t WHERE t.startDate = :startDate")
        , @NamedQuery(name = "TransactionInfo.findByEndDate", query = "SELECT t FROM TransactionInfo t WHERE t.endDate = :endDate")
        , @NamedQuery(name = "TransactionInfo.findByDuration", query = "SELECT t FROM TransactionInfo t WHERE t.duration = :duration")
        , @NamedQuery(name = "TransactionInfo.findByPrice", query = "SELECT t FROM TransactionInfo t WHERE t.price = :price")
        , @NamedQuery(name = "TransactionInfo.findByTypeOfPayment", query = "SELECT t FROM TransactionInfo t WHERE t.typeOfPayment = :typeOfPayment")})
public class TransactionInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "state")
    private String state;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "duration")
    private BigInteger duration;
    @Column(name = "price")
    private Integer price;
    @Column(name = "type_of_payment")
    private String typeOfPayment;
    @OneToMany(mappedBy = "transactionId")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "transactionId")
    private Collection<Complaint> complaintCollection;
    @OneToMany(mappedBy = "transactionId")
    private Collection<Review> reviewCollection;
    @OneToMany(mappedBy = "exchangedBy")
    private Collection<TransactionInfo> transactionInfoCollection;
    @JoinColumn(name = "exchanged_by", referencedColumnName = "id")
    @ManyToOne
    private TransactionInfo exchangedBy;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    private Service serviceId;
    @JoinColumn(name = "started_by", referencedColumnName = "id")
    @ManyToOne
    private UserTable startedBy;

    @Transient
    public static final String PENDING_STATE = "pending";
    @Transient
    public static final String ACCEPTED_STATE = "accepted";
    @Transient
    public static final String ON_PROGRESS_STATE = "on_progress";
    @Transient
    public static final String REJECTED_STATE = "rejected";
    @Transient
    public static final String COMPLETED_STATE = "completed";
    @Transient
    public static final String EXTENDED_STATE = "extended";
    @Transient
    public static final String LATE_STATE = "late";
    @Transient
    public static final String POSTPONED = "postpone";

    public TransactionInfo() {
    }

    public TransactionInfo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(BigInteger duration) {
        this.duration = duration;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Complaint> getComplaintCollection() {
        return complaintCollection;
    }

    public void setComplaintCollection(Collection<Complaint> complaintCollection) {
        this.complaintCollection = complaintCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<TransactionInfo> getTransactionInfoCollection() {
        return transactionInfoCollection;
    }

    public void setTransactionInfoCollection(Collection<TransactionInfo> transactionInfoCollection) {
        this.transactionInfoCollection = transactionInfoCollection;
    }

    public TransactionInfo getExchangedBy() {
        return exchangedBy;
    }

    public void setExchangedBy(TransactionInfo exchangedBy) {
        this.exchangedBy = exchangedBy;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public UserTable getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(UserTable startedBy) {
        this.startedBy = startedBy;
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
        if (!(object instanceof TransactionInfo)) {
            return false;
        }
        TransactionInfo other = (TransactionInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.serviceexchangeentities.TransactionInfo[ id=" + id + " ]";
    }

}
