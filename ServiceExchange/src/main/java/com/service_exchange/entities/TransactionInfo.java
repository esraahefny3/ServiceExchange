/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import org.jetbrains.annotations.Nullable;

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
    @Nullable
    private Integer id;
    @Column(name = "type")
    @Nullable
    private String type;
    @Column(name = "state")
    @Nullable
    private String state;
    @Column(name = "start_date")
    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Nullable
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "duration")
    @Nullable
    private BigInteger duration;
    @Column(name = "price")
    @Nullable
    private Integer price;
    @Column(name = "type_of_payment")
    @Nullable
    private String typeOfPayment;


    @Column(name = "jop_file")
    @Nullable
    private String jopFile;
    @Column(name = "descrption")
    @Nullable
    private String descrption;
    @OneToMany(mappedBy = "transactionId")
    @Nullable
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "transactionId")
    @Nullable
    private Collection<Complaint> complaintCollection;
    @OneToMany(mappedBy = "transactionId")
    @Nullable
    private Collection<Review> reviewCollection;
    @OneToMany(mappedBy = "exchangedBy")
    @Nullable
    private Collection<TransactionInfo> transactionInfoCollection;
    @JoinColumn(name = "exchanged_by", referencedColumnName = "id")
    @ManyToOne
    @Nullable
    private TransactionInfo exchangedBy;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne
    @Nullable
    private Service serviceId;
    @JoinColumn(name = "started_by", referencedColumnName = "id")
    @ManyToOne
    @Nullable
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
    public static final String COMPLETED_APPROVED_STATE = "completed and approved";
    @Transient
    public static final String NOT_COMPLETED_STATE = "not completed";
    @Transient
    public static final String FAILED_STATE = "failed";
    @Transient
    public static final String POSTPONED = "postpone";

    public TransactionInfo() {
    }

    public TransactionInfo(Integer id) {
        this.id = id;
    }

    @Nullable
    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(@Nullable String descrption) {
        this.descrption = descrption;
    }
    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @Nullable
    public String getJopFile() {
        return jopFile;
    }

    public void setJopFile(@Nullable String jopFile) {
        this.jopFile = jopFile;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public void setState(@Nullable String state) {
        this.state = state;
    }

    @Nullable
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nullable Date startDate) {
        this.startDate = startDate;
    }

    @Nullable
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@Nullable Date endDate) {
        this.endDate = endDate;
    }

    @Nullable
    public BigInteger getDuration() {
        return duration;
    }

    public void setDuration(@Nullable BigInteger duration) {
        this.duration = duration;
    }

    @Nullable
    public Integer getPrice() {
        return price;
    }

    public void setPrice(@Nullable Integer price) {
        this.price = price;
    }

    @Nullable
    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(@Nullable String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    @Nullable
    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(@Nullable Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<Complaint> getComplaintCollection() {
        return complaintCollection;
    }

    public void setComplaintCollection(@Nullable Collection<Complaint> complaintCollection) {
        this.complaintCollection = complaintCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(@Nullable Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @Nullable
    @XmlTransient
    public Collection<TransactionInfo> getTransactionInfoCollection() {
        return transactionInfoCollection;
    }

    public void setTransactionInfoCollection(@Nullable Collection<TransactionInfo> transactionInfoCollection) {
        this.transactionInfoCollection = transactionInfoCollection;
    }

    @Nullable
    public TransactionInfo getExchangedBy() {
        return exchangedBy;
    }

    public void setExchangedBy(@Nullable TransactionInfo exchangedBy) {
        this.exchangedBy = exchangedBy;
    }

    @Nullable
    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(@Nullable Service serviceId) {
        this.serviceId = serviceId;
    }

    @Nullable
    public UserTable getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(@Nullable UserTable startedBy) {
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
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.service_exchange.serviceexchangeentities.TransactionInfo[ id=" + id + " ]";
    }

}
