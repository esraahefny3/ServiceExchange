package com.service_exchange.utal.firebasenotificationsutil;

import com.google.gson.annotations.SerializedName;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegate;
import com.service_exchange.api_services.bussinessdaodelegates.user.UserDelegateInterface;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.NotificationDto;
import com.service_exchange.api_services.factories.AppFactory;
import com.service_exchange.entities.UserTable;
import org.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Generated;
import javax.persistence.Transient;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NotificationData {

    @SerializedName("body")
    private Object objectData;

    @SerializedName("type")
    private String type;


    @SerializedName("description")
    private String description;

    @Transient
    public static String transactionType="transactionType";
    @Transient
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String createMessageFirebaseDescription(MessageGeneralDto messageGeneralDto)
    {
        UserDelegateInterface userDelegateInterfaceImpl=AppFactory.getuserDelegateInterfaceInstance();
        UserTable sender=userDelegateInterfaceImpl.getUserById(messageGeneralDto.getSenderId());
        if(messageGeneralDto.getText().length()>30) {
//            StringBuilder result = new StringBuilder();
//            result.append("Message from ");
//            result.append(reciever.getName());
//            result.append("/n")
            return "Message from " + sender.getName() + "\n" +
                    messageGeneralDto.getText().substring(0, 30) + "...";
        }
        else {
            return "Message from " + sender.getName() + "\n" +
                    messageGeneralDto.getText();
        }
    }
}