package com.service_exchange.api_services.dao.transaction.nour;

import java.math.BigInteger;
import java.util.Date;

public class TransactionDtoNour {

    private Integer id;
    private String type;
    private String state;
    private Date startDate;
    private Date endDate;
    private BigInteger duration;
    private Integer price;
//    private String typeOfPayment;

    private Integer serviceId;
    private Integer startedByUser;

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

//    public String getTypeOfPayment() {
//        return typeOfPayment;
//    }
//
//    public void setTypeOfPayment(String typeOfPayment) {
//        this.typeOfPayment = typeOfPayment;
//    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getStartedByUser() {
        return startedByUser;
    }

    public void setStartedByUser(Integer startedByUser) {
        this.startedByUser = startedByUser;
    }
}
