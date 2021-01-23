package com.albumbazaar.albumbazar.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.albumbazaar.albumbazar.services.SMSService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Qualifier("smsService")
public class SMSServiceImpl implements SMSService {

    @Value("${TEXT.LOCAL.APIKEY}")
    private String apiKey;

    @Override
    public String sendSMS(String messageToSend, String number) {

        try {
            // Construct data
            String apiKey = "apikey=" + this.apiKey;
            String message = "&message=" + messageToSend;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + number;
            String test = "&test=" + true;

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender + test;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            return "Error " + e;
        }

    }

    @Override
    public String sendSMSAsync(String message, String number) {

        return null;
    }

}
