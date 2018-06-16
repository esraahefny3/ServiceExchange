package com.service_exchange.utal.firebasenotificationsutil;

import com.google.gson.annotations.SerializedName;
import com.service_exchange.api_services.dao.dto.NotificationDto;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("body")
    private NotificationDto notificationDtoDetail;

    @SerializedName("title")
    private String mTitle;

    public NotificationDto getNotificationDtoDetail() {
        return notificationDtoDetail;
    }

    public void setNotificationDtoDetail(NotificationDto notificationDtoDetail) {
        this.notificationDtoDetail = notificationDtoDetail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }


}