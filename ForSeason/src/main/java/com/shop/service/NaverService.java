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
		System.out.println("service");
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		String line = "", result = "";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//POST 요청을 위해 기본값이 false인 setOutput을 true로 셋팅
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			//POST 요청할 때에 필요한 파라미터를 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=i2iAIOgbmOWXyr_C5kek");
			sb.append("&client_secret=dJ6ieSBcG3");
			sb.append("&redirect_uri=http://localhost:8181/callback");
			sb.append("&code="+authorize_code);
			sb.append("&state=STATE");
			bw.write(sb.toString());
			bw.flush();
			//결과 코드가 200일 경우 성공
//			System.out.println("responseCode : "+conn.getResponseCode());
			//요청을 통해 얻은 JSON타입의 Response 메세지
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((line = br.readLine()) != null) {
				result += line;
			}
//			System.out.println("responseBody : "+result);
			//JSON 데이터 파싱
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
//			System.out.println("access_token : "+access_Token);
//			System.out.println("refresh_token : "+refresh_Token);
			br.close();
			bw.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println(access_Token);
		
		return access_Token;
	}

	public HashMap<String, String> getUserInfo (String access_Token){
		HashMap<String, String> userInfo = new HashMap<>();
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		String line = "", result = "";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			//요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer "+access_Token);
//			System.out.println("responseCode : "+conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			System.out.println(br);
			while((line = br.readLine()) != null) {
				result += line;
			}
//			System.out.println("response body : "+result);
			
			JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        JsonObject naver_response = element.getAsJsonObject().get("response").getAsJsonObject();	

	        System.out.println(naver_response);
	        userInfo.put("user_id", naver_response.get("id").toString().replaceAll("\"",""));
	        userInfo.put("nickname", naver_response.get("name").toString().replaceAll("\"",""));
	        userInfo.put("email", naver_response.get("email").toString().replaceAll("\"",""));
	        
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return userInfo;
	}
}
