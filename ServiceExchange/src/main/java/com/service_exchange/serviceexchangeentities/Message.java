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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
        , @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id")
        , @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text")
        , @NamedQuery(name = "Message.findByAttachment", query = "SELECT m FROM Message m WHERE m.attachment = :attachment")
        , @NamedQuery(name = "Message.findByDate", query = "SELECT m FROM Message m WHERE m.date = :date")
        , @NamedQuery(name = "Message.findByIsSeen", query = "SELECT m FROM Message m WHERE m.isSeen = :isSeen")
        , @NamedQuery(name = "Message.findBySeenDate", query = "SELECT m FROM Message m WHERE m.seenDate = :seenDate")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "text")
    private String text;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "is_seen")
    private Short isSeen;
    @Column(name = "seen_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seenDate;
    @JoinColumn(name = "complaint_id", referencedColumnName = "id")
    @ManyToOne
    private Complaint complaintId;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne
    private TransactionInfo transactionId;
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    @ManyToOne
    private UserTable receiverId;
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    @ManyToOne
    private UserTable senderId;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Short getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(Short isSeen) {
        this.isSeen = isSeen;
    }

    public Date getSeenDate() {
        return seenDate;
    }

    public void setSeenDate(Date seenDate) {
        this.seenDate = seenDate;
    }

    public Complaint getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Complaint complaintId) {
        this.complaintId = complaintId;
    }

    public TransactionInfo getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionInfo transactionId) {
        this.transactionId = transactionId;
    }

    public UserTable getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserTable receiverId) {
        this.receiverId = receiverId;
    }

    public UserTable getSenderId() {
        return senderId;
    }

    public void setSenderId(UserTable senderId) {
        this.senderId = senderId;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iti.serviceexchange.Message[ id=" + id + " ]";
    }

}
