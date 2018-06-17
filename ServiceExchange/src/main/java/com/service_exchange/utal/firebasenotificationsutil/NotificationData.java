package com.service_exchange.utal.firebasenotificationsutil;

import com.google.gson.annotations.SerializedName;
import com.service_exchange.api_services.dao.dto.NotificationDto;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("body")
    private Object objectData;

    @SerializedName("type")
    private String type;

    @SerializedName("userNameOfMessage")
    private String userNameOfMessage;

    public static String transactionType="transactionType";
    public static String messageType="messageType";

    public Object getObjectData() {
        return objectData;
    }

    public void setObjectData(Object objectData) {
        this.objectData = objectData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserNameOfMessage() {
        return userNameOfMessage;
    }

    public void setUserNameOfMessage(String userNameOfMessage) {
        this.userNameOfMessage = userNameOfMessage;
    }
}