package com.service_exchange.api_services.dao.transaction;

import java.math.BigInteger;
import java.util.Date;

public class TransactionDto {

    private Integer id;
    private String type;
    private String state;
    private Date startDate;
    private Date endDate;
    private BigInteger duration;
    private Integer price;
    private String serviceName;
    private String otherUserName;
    private boolean isServiceProvider;
    private String jopFile;
    private String otherUserImage;
    private String serviceDescription;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }

    public boolean isServiceProvider() {
        return isServiceProvider;
    }

    public void setServiceProvider(boolean serviceProvider) {
        isServiceProvider = serviceProvider;
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

    public String getJopFile() {
        return jopFile;
    }

    public void setJopFile(String jopFile) {
        this.jopFile = jopFile;
    }

    public String getOtherUserImage() {
        return otherUserImage;
    }

    public void setOtherUserImage(String otherUserImage) {
        this.otherUserImage = otherUserImage;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
}
