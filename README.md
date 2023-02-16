# 👩‍👦‍👦 ForSeason
![alt text](images/mainLogo.png) <br>
팀장 : 김지영 </br>
팀원 : 조민수, 설재경 </br>
개발 기간 :  2023.01.02 ~ 2023.02.17 (총 46일) </br>
주제 : 온라인 옷 쇼핑몰 </br>

# 개요  

- 시연 영상 : http://youtube.com/

# 🗒️ 개발 목적 
## 기획 목적
1. 무난히 채택되는 웹쇼핑몰이라는 주제 우리만의 깊이로 담아낸다.
2. 백앤드의 영역에서 기술적인 부분에 가장 효과적인 증명을 찾고자 했다.

## 주제 선정
 1. 성별 > 옷분류 > 세부 옷분류 > 상품 > 색상 > size 카테고리를 단계별 정의, 깊은  multilayer 구현 
 2. multilayer 구현으로 통한 광범위한 데이터 재고 시스템 구현(출고, 입고)
 3. 상품 데이터가 광범위하게 증가에도, 관리용이한 유저경험 서비스

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
4. Maven을 이용하여 빌드하고 실행합니다.

# 🧐 사용 방법
주의 : 꼭 실행전에 해당 라이브러리를 사용하기 위해선 아래 API를 등록 후 키값과 Redirect URL 를 수정 해주셔야 합니다. <br>
Kakao API 등록 : https://developers.kakao.com/docs/latest/ko/index<br> 
Naver API 등록 : https://developers.naver.com/docs/common/openapiguide/appconf.md#api-%EC%84%A4%EC%A0%95<br>
Naver CLOVA Chatbot API : https://www.ncloud.com/product/aiService/chatbot <br>
Google Email API 등록 : https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api?hl=ko<br>
Iamport API 등록 : https://portone.gitbook.io/docs/<br>
Weather API 등록 : https://blog.codef.io/weather_api/<br> 

## application.properties<br> 
![alt text](images/API/applicationProperties.png)
- 위치 : src/main/resources/templates/application.properties
 Intellij : mybatis.mapper-locations=classpath:mybatis/*.xml <br>
 Eclipse  : mybatis.mapper-locations=com/admin/mybatis/*.xml <br> 
## KAKAO Login, Map API
- 위치 :  /src/main/service/KakaoService.class <br>
기입사항 : REST KEY
![alt text](images/API/kakaoServiceToken.png)
- 위치 : src/main/resources/templates/main.html <br>
기입사항 : Javascript KEY
![alt text](images/API/kakaoMapKey.png) 
## NAVER Login API
- 위치 : /src/main/service/naverService.class <br>
기입사항 : REST KEY
![alt text](images/API/naverServiceToken.png)
## NAVER CLOVA CHATBOT API
- 위치 : /src/main/frame/ChatBotUtil.class <br>
기입사항 : REST KEY <br>
![alt text](images/API/chatBotToken.png)
![alt text](images/API/naverClovaKey.png)

## Weather API
- 위치 : /src/main/controller/DataController.class <br>
기입사항 : 일반 인증키
![alt text](images/API/weatherToken.png)



# 프로젝트의 주요기능 및 특징
프로젝트에서 제공하는 다차원적인 제품 분류 기능, 재고 관리 기능, 주문 처리 기능 등의 기능을 설명하고, 이를 통해 고객들이 더욱 편리하게 쇼핑을 할 수 있도록 하는 것을 강조할 수 있습니다.

# 👍 WBS
![alt text](images/wbs/wbsMain.png)
![alt text](images/wbs/wbsInput.png)

# 🙋🏻 웹 구성요소

<details open>
<summary>User Detail View</summary>

### Main Page 
- HOME
![alt text](images/userImages/UserMainPage.png)
![alt text](images/userImages/userHomeBody.png)

- Best, Map
![alt text](images/userImages/userBestPage.png)
![alt text](images/userImages/shopMapPage.png)
### Login Page
- Login, Sign Up
![alt text](images/userImages/userLoginPage.png) 
![alt text](images/userImages/userKakaoLoginOkPage.png) 
### UserInfo 
- Order Page
![alt text](images/userImages/userOrder.png) 
![alt text](images/userImages/userOrderDetail.png) 
- WishList, Review Page
![alt text](images/userImages/userWishListPage.png) 
![alt text](images/userImages/userReviewPage.png) 

- Coupon, Qna Page
![alt text](images/userImages/userCoupon.png) 
![alt text](images/userImages/userQnAPage.png) 
- UserInfo Change
![alt text](images/userImages/userCheckPassword.png) 
![alt text](images/userImages/userChangeInfo.png) 
![alt text](images/userImages/userDelete.png) 

### Item Page
- ItemDetail(Wish ADD)
![alt text](images/userImages/userWishView.png) 
- ItemSearch
![alt text](images/userImages/userSearchPage.png) 
- order ADD
![alt text](images/userImages/orderAdd.png) 


### QnA Page
- QnA Page
![alt text](images/userImages/qnaADD.png)




</details>
<details open> 

<summary>Admin Detail View </summary>

### Login Page
![alt text](images/adminImages/adminLoginPage.png) 
### Main Page 
![alt text](images/adminImages/AdminMainPage.png) 
### Chart Click Event
![alt text](images/adminImages/AdminChartDayClick.png) 
![alt text](images/adminImages/AdminChartClickEvent.png) 
### Category Page
![alt text](images/adminImages/AdminCategoryClickEventPage.png) 
### Item Add Click 
![alt text](images/adminImages/itemAddPage.png) 
### UserPush Coupon And Mail Page
![alt text](images/adminImages/UserPushCouponAndMail.png) 
### Discount Click
![alt text](images/adminImages/checkDiscountPage.png) 
### QnA Page - Reply Page
![alt text](images/adminImages/qnaPage.png) ![alt text](images/adminImages/replyPage.png) 
### Datail Click
![alt text](images/adminImages/qnaDatailPage.png) 


</details>


# 👩🏻‍💻 프로젝트 과정 

김지영 : - 수정예정 <br>
조민수 : - 수정예정 <br>
설재경 : - 수정예정 <br>


# 👀 테스트 및 배포 


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
