package com.shop.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class NaverService {
    public String getAccessToken(String authorize_code) {
        String access_Token = "";
        String reqURL = "https://nid.naver.com/oauth2.0/token";
        String line = "", result = "";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=####CLIENT ID####");
            sb.append("&client_secret=####SECRET KEY####");
            sb.append("&redirect_uri=http://27.96.130.136:80/oauth/login"); // redirect 주소
            sb.append("&code=" + authorize_code);
            sb.append("&state=STATE");
            bw.write(sb.toString());
            bw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    public HashMap<String, String> getUserInfo(String access_Token) {
        HashMap<String, String> userInfo = new HashMap<>();
        String reqURL = "https://openapi.naver.com/v1/nid/me";
        String line = "", result = "";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject naver_response = element.getAsJsonObject().get("response").getAsJsonObject();
            userInfo.put("user_id", naver_response.get("id").toString().replaceAll("\"", ""));
            userInfo.put("nickname", naver_response.get("name").toString().replaceAll("\"", ""));
            userInfo.put("email", naver_response.get("email").toString().replaceAll("\"", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
