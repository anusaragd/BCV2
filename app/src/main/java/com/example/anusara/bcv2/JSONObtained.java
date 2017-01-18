package com.example.anusara.bcv2;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by anusara on 15-Sep-16.
 */
public class JSONObtained {
//    public static final String BASE_URL = "http://192.168.1.35/breast-cancer/";
//    public static final String BASE_URL = "http://192.168.1.33/breast-cancer/";
    public static final String BASE_URL = "http://192.168.1.37/breast-cancer/";
//    public static final String BASE_URL = "http://192.168.43.180/breast-cancer/";
//    public static final String BASE_URL = "http://172.19.237.81/breast-cancer/";

//    public static final String BASE_URL = "http://192.168.1.44/breast-cancer/";
//    public static final String BASE_URL = "http://192.168.1.37/breast-cancer/";
//    public static final String BASE_URL = "http://172.19.145.41/breast-cancer/";
//    public static final String BASE_URL = "http://192.168.43.180/breast-cancer/";
//    public static final String BASE_URL = "http://172.19.245.180/breast-cancer/";

    private static OkHttpClient client = null;

    public static OkHttpClient getInstance() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)// connect timeout
                    .writeTimeout(200, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS) // socket timeout
                    .build();

        }

        return client;
    }
    public static Request getRequest(HttpUrl url){

        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }
    public static Request postRequest(HttpUrl url, RequestBody formBody){

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        return request;
    }

    public static String getAbsoluteUrl(String relativeUrl) {

        return BASE_URL + relativeUrl;
    }

}

