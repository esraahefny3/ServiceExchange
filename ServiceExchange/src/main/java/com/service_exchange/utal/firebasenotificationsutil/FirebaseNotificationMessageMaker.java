package com.service_exchange.utal.firebasenotificationsutil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.service_exchange.api_services.dao.dto.NotificationDto;
import com.service_exchange.entities.UserTable;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
public class FirebaseNotificationMessageMaker {

   private static String  firebaseSendUrlString="https://fcm.googleapis.com/fcm/send";
   private static String firebaseApplicationAuthorizationKeyString="AIzaSyBmT6CUc3S0UDzYyANcZE8D2DMVVZxrQms";

    public static int sendFirebaseNotificationMessageToUser(NotificationDto notificationDto, UserTable userTable)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setNotificationDtoDetail(notificationDto);
        notificationData.setmTitle("Hello Firebase Push Notification");
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(userTable.getFirebaseAuthorizationKey());


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = null;
        try {
            input = new StringEntity(json);
            input.setContentType("application/json");

            // server key of your firebase project goes here in header field.
            // You can get it from firebase console.

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyString);
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);

//            if (response.getStatusLine().getStatusCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatusLine().getStatusCode());
//            } else if (response.getStatusLine().getStatusCode() == 200) {
//
//                System.out.println("response:" + EntityUtils.toString(response.getEntity()));
//
//            }

           return response.getStatusLine().getStatusCode();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static int sendFirebaseNotificationMessageToTopic(NotificationDto notificationDto, UserTable userTable,String topic)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setNotificationDtoDetail(notificationDto);
        notificationData.setmTitle("Hello Firebase Push Notification");
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(userTable.getFirebaseAuthorizationKey());


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = null;
        try {
            input = new StringEntity(json);
            input.setContentType("application/json");

            // server key of your firebase project goes here in header field.
            // You can get it from firebase console.

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyString);
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);

//            if (response.getStatusLine().getStatusCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatusLine().getStatusCode());
//            } else if (response.getStatusLine().getStatusCode() == 200) {
//
//                System.out.println("response:" + EntityUtils.toString(response.getEntity()));
//
//            }

            return response.getStatusLine().getStatusCode();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public static int sendFirebaseNotificationMessageToUserTry(NotificationDto notificationDto, String user,String authKey)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setNotificationDtoDetail(notificationDto);
        notificationData.setmTitle("Hello Firebase Push Noti");
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(user);


        Gson gson = new Gson();
        Type type = new TypeToken<NotificationRequestModel>() {
        }.getType();

        String json = gson.toJson(notificationRequestModel, type);

        StringEntity input = null;
        try {
            input = new StringEntity(json);
            input.setContentType("application/json");

            // server key of your firebase project goes here in header field.
            // You can get it from firebase console.

            postRequest.addHeader("Authorization", "key="+authKey);
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);

//            if (response.getStatusLine().getStatusCode() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatusLine().getStatusCode());
//            } else if (response.getStatusLine().getStatusCode() == 200) {
//
//                System.out.println("response:" + EntityUtils.toString(response.getEntity()));
//
//            }

            return response.getStatusLine().getStatusCode();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
