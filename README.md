# 👩‍👦‍👦 ForSeason

팀장 : 김지영<br>
팀원 : 조민수, 설재경
개발 기간은 2023.01.02 ~ 2023.02.17 (총 46일) </br>
주제 : 온라인 옷 쇼핑몰

# 개요  
- 시연 영상 : http://youtube.com/

# 🎩 개발 환경 / 시스템 구성

 | 항목 | 내용 |
 | --- | ---|
 | 언어 | Java, Python, HTML/CSS, JavaScript |
 | 서버 | NaverCloudPlatForm, Apache Tomcat |
 | 프레임워크 | SpirngBoot, Mybatis, Thymeleaf |
 | DB | MySQL |
 | IDE | Eclipse, Intellij, GitBash, MySQL Workbench |
 | 협업 도구 | Git-hub, Notion, Zoom, Discord, KakaoTalk |
 | API 또는 라이브러리 | Google :  EMAIL <br>Kakao : Login, Map <br> Naver : Login, Chatbot <br> Iamport : KakaoPay <br> 공공데이터 : Weather <br> |
 | VERSION | Java JDK : 11.0.17 <br> Maven : 2.7.7 <br> Servlet : 3.0.1 <br> Mysql : 8.0.31 <br> Intellij : 2022.3.2 (Ultimate Edition) <br> Eclipse IDE : 2020-12 (4.18.0)|            

## ERD 구조 
![alt text](erd.png)
-  https://www.erdcloud.com/d/7PDjo2FzznEwfmLCZ

# 🤓 설치 방법
프로젝트를 로컬 환경에서 실행하는 방법을 설명하는 섹션입니다. 

1. 저장소를 클론합니다.
2. 해당 프로젝트를 IDE(예: Eclipse, Intellij 등)에서 엽니다.
3. DB 설정 파일(application.properties)에서 DB 정보를 수정합니다. <br>
 Intellij : mybatis.mapper-locations=classpath:mybatis/*.xml <br>
 Eclipse  : mybatis.mapper-locations=com/admin/mybatis/*.xml <br> 
4. Maven을 이용하여 빌드하고 실행합니다.

# 🧐 사용 방법
주의 : 꼭 실행전에 해당 라이브러리를 사용하기 위해선 아래 API를 등록 후 키값과 Redirect URL 를 수정 해주셔야 합니다. <br>
Kakao API 등록 : https://developers.kakao.com/docs/latest/ko/index<br> 
Naver API 등록 : https://developers.naver.com/docs/common/openapiguide/appconf.md#api-%EC%84%A4%EC%A0%95<br>
Google Email API 등록 : https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api?hl=ko<br>
Iamport API 등록 : https://portone.gitbook.io/docs/<br> 


<details open>
<summary>User Detail View</summary>

### Login Page
![alt text](./userImages/UserMainPage.png) 
### Main Page 
![alt text](AdminMainPage.png) 
### Chart Click Event
![alt text](AdminChartDayClick.png) 
![alt text](AdminChartClickEvent.png) 
### Category Page
![alt text](AdminCategoryClickEventPage.png) 
### Item Add Click 
![alt text](itemAddPage.png) 
### UserPush Coupon And Mail Page
![alt text](UserPushCouponAndMail.png) 
### Discount Click
![alt text](checkDiscountPage.png) 
### QnA Page - Reply Page
![alt text](qnaPage.png) ![alt text](replyPage.png) 
### Datail Click
![alt text](qnaDatailPage.png) 



</details>
<details open> 

<summary>Admin Detail View </summary>

### Login Page
![alt text](./adminImages/adminLoginPage.png) 
### Main Page 
![alt text](./adminImages/AdminMainPage.png) 
### Chart Click Event
![alt text](./adminImages/AdminChartDayClick.png) 
![alt text](./adminImages/AdminChartClickEvent.png) 
### Category Page
![alt text](./adminImages/AdminCategoryClickEventPage.png) 
### Item Add Click 
![alt text](./adminImages/itemAddPage.png) 
### UserPush Coupon And Mail Page
![alt text](./adminImages/UserPushCouponAndMail.png) 
### Discount Click
![alt text](./adminImages/checkDiscountPage.png) 
### QnA Page - Reply Page
![alt text](./adminImages/qnaPage.png) ![alt text](./adminImages/replyPage.png) 
### Datail Click
![alt text](./adminImages/qnaDatailPage.png) 


</details>

# 👍 WBS


# 프로젝트 과정 

김지영 : <br>
조민수 : 파이썬을 이용한 이미지 크롤링을 통해 상품 카테고리 확장 <br>
설재경 : <br>


# 테스트 및 배포 


# 기여 방법

프로젝트에 기여하는 방법을 설명하는 섹션입니다. 아래와 같은 절차를 따릅니다.

1. 이 저장소를 포크합니다.
2. 새로운 브랜치를 만들어 개선 작업을 합니다.
3. 변경 사항을 커밋합니다.
4. 새로운 풀 리퀘스트를 생성합니다.

# 라이선스

프로젝트에 대한 라이선스 정보를 적습니다. 

[라이선스 정보]

# 참고자료
Intellij Mybatis -  https://kyun2.tistory.com/69 <br>
Selenium - https://wikidocs.net/177133 <br>


# 트러블 슈팅 
