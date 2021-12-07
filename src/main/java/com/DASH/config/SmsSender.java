package com.DASH.config;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

@Component
public class SmsSender {
    public void sendSms(String message, String number){
        try {
            String apiKey = "pFKbHzt2fGV4wy1WYJhZX6nPDC5Ljox3Q9cvqIuM0irUmdEa7NAYG34chFHMxyrnawRVQ8isKzo2Wlqv";
            String senderId="FSTSMS";
            message = URLEncoder.encode(message, "UTF-8");
            String language = "english";
            String route = "p";
            String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+senderId+"&message="+message+"&route="+route+"&numbers="+number;

            URL url = new URL(myUrl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("cache-control", "no-cache");

            con.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}