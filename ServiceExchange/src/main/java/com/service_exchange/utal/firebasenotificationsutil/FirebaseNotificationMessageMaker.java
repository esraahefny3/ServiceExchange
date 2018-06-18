package com.service_exchange.utal.firebasenotificationsutil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.NotificationDto;
import com.service_exchange.entities.UserTable;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
public class FirebaseNotificationMessageMaker {

    private static String firebaseSendUrlString = "https://fcm.googleapis.com/fcm/send";
    private static String firebaseApplicationAuthorizationKeyStringAndroid = "AIzaSyD_1-Un77E_MzwikLPBMll1YTYMY6c4tAo";
    private static String firebaseApplicationAuthorizationKeyStringWeb = "AIzaSyBmT6CUc3S0UDzYyANcZE8D2DMVVZxrQms";

    public static int sendFirebaseNotificationMessageToUserAndroid(Object objectData, String userToken,String typeData,String description)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setObjectData(objectData);
        notificationData.setType(typeData);
        notificationData.setDescription(description);
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(userToken);


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

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyStringAndroid);
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

    public static int sendFirebaseNotificationMessageToTopicAndroid(Object objectData,String typeData, String topic,String description) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setObjectData(objectData);
        notificationData.setType(typeData);
        notificationData.setDescription(description);
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo("/topics/"+topic);


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

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyStringAndroid);
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


    //==============================================================================================================================

    public static int sendFirebaseNotificationMessageToUserWeb(Object objectData, String userToken,String typeData,String description)
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setObjectData(objectData);
        notificationData.setType(typeData);
        notificationData.setDescription(description);
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo(userToken);


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

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyStringWeb);
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

    public static int sendFirebaseNotificationMessageToTopicWeb(Object objectData,String typeData, String topic,String description)  {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setObjectData(objectData);
        notificationData.setType(typeData);
        notificationData.setDescription(description);
        notificationRequestModel.setData(notificationData);
        notificationRequestModel.setTo("/topics/"+topic);


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

            postRequest.addHeader("Authorization", "key="+firebaseApplicationAuthorizationKeyStringWeb);
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


    //==============================================================================================================================
    public static int sendFirebaseNotificationMessageToUserTry(Object objectData,String typeData, String user, String authKey) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(
                firebaseSendUrlString);

        // we already created this model class.
        // we will convert this model class to json object using google gson library.

        NotificationRequestModel notificationRequestModel = new NotificationRequestModel();
        NotificationData notificationData = new NotificationData();

        notificationData.setObjectData(objectData);
        notificationData.setType(typeData);
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

            postRequest.addHeader("Authorization", "key=" + authKey);
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
