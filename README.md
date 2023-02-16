# 👩‍👦‍👦 ForSeason
![alt text](images/mainLogo.png) <br>
팀장 : 김지영 </br>
팀원 : 조민수, 설재경 </br>
개발 기간 :  2023.01.02 ~ 2023.02.17 (총 46일) </br>
주제 : EIMS 기능을 도입한 패션 리테일 세일즈 마켓 </br>

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

# ERD 구조 

<details>
<summary>view images</summary>

![alt text](erd.png)
</details>

-  https://www.erdcloud.com/d/7PDjo2FzznEwfmLCZ
# UI

<details>
<summary>view images</summary>

![alt text](images/AdminUI.png) </br>
![alt text](images/UserUI.png)</br>
</details>

# 🤓 설치 방법
1. 깃 클라이언트를 설치합니다.
2. 이 저장소를 clone합니다. `$ git clone https://github.com/5group/ForSeason.git`
3. 프로젝트 디렉토리로 이동합니다. `$ cd ForSeason`,`$ cd Admin_ForSeason`
4. 필요한 라이브러리를 설치합니다. `$ npm install`
5. 프로젝트를 실행합니다. `$ npm start`

# 🧐 사용 방법
주의 : 꼭 실행전에 해당 라이브러리를 사용하기 위해선 아래 API를 등록 후 키값과 Redirect URL 를 수정 해주셔야 합니다. <br>
1. Kakao API 등록 : https://developers.kakao.com/docs/latest/ko/index 
2. Naver API 등록 : https://developers.naver.com/docs/common/openapiguide/appconf.md#api-%EC%84%A4%EC%A0%95 
3. Naver CLOVA Chatbot API : https://www.ncloud.com/product/aiService/chatbot 
4. Google Email API 등록 : https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api?hl=ko 
5. Iamport API 등록 : https://portone.gitbook.io/docs/ 
6. Weather API 등록 : https://blog.codef.io/weather_api/  


<details>
<summary>View Setting Images</summary>

## application.properties<br>


<details>
<summary>view images</summary>

![alt text](images/API/applicationProperties.png)
</details>

- 위치 : src/main/resources/templates/application.properties <br>
 *- Intellij : mybatis.mapper-locations=classpath:mybatis/*.xml <br>
 *- Eclipse  : mybatis.mapper-locations=com/admin/mybatis/*.xml <br> 
## KAKAO Login, Map API
- 위치 :  /src/main/service/KakaoService.class <br>
기입사항 : REST KEY<br>

<details>
<summary>view images</summary>

![alt text](images/API/kakaoServiceToken.png)
</details>

- 위치 : src/main/resources/templates/main.html <br>
 기입사항 : Javascript KEY <br>
<details>
<summary>view images</summary>

![alt text](images/API/kakaoMapKey.png) 

</details>

## NAVER Login API
- 위치 : /src/main/service/naverService.class <br>
기입사항 : REST KEY<br>

<details>
<summary>view images</summary>

![alt text](images/API/naverServiceToken.png)

</details>

## NAVER CLOVA CHATBOT API
- 위치 : /src/main/frame/ChatBotUtil.class <br>
기입사항 : REST KEY <br>

<details>
<summary>view images</summary>

![alt text](images/API/chatBotToken.png)<br>
![alt text](images/API/naverClovaKey.png)<br>
</details>

## Weather API
- 위치 : /src/main/controller/DataController.class <br>
기입사항 : 일반 인증키<br>
<details>
<summary>view images</summary>

![alt text](images/API/weatherToken.png)
</details>

</details>

# 🪄 기여 방법

1. 이 저장소를 포크합니다.
2. 새로운 브랜치를 만들어 개선 작업을 합니다.
3. 변경 사항을 커밋합니다.
4. 새로운 풀 리퀘스트를 생성합니다.

# 프로젝트의 주요기능 및 특징
프로젝트에서 제공하는 다차원적인 제품 분류 기능, 재고 관리 기능, 주문 처리 기능 등의 기능을 설명하고, 이를 통해 고객들이 더욱 편리하게 쇼핑을 할 수 있도록 하는 것을 강조할 수 있습니다.

# 👍 WBS

<details>
<summary>view images</summary>

![alt text](images/wbs/wbsMain.png)
![alt text](images/wbs/wbsInput.png)
</details>


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

hi


# 👩🏻‍💻 프로젝트 과정 


<table>
  <tr>
    <td>이름</td>
    <td>Part Table</td>
    <td>기능</td>
  </tr>
  <tr>
    <td rowspan="20">조민수</td>
    <td rowspan="5">회원</td>
    <td>CRUD 구현 </td>
  </tr>
  <tr>
    <td>회원 탈퇴, 아이디/비밀번호 찾기, 정보 변경 및 패스워드 변경  기능구현 </td>
  </tr>
  <tr>
    <td>회원 로그인(Kakao Login) 및 로그아웃, 회원가입, 회원탈퇴 구현   </td>
  </tr>
  <tr>
    <td>주문 내역 조회, 주문상세내역 조회, 쿠폰 조회 구현 </td>
  </tr>
  <tr>
    <td>(ADMIN) 회원 목록에서 쿠폰 발급 및 광고메일 발송 기능구현 </td>
  </tr>
  <tr>
    <td rowspan="3">주문</td>
  </tr>
  <tr>
    <td>CRUD 구현</td>
  </tr>
  <tr>
    <td>카카오 페이 결제 구현 </td>
  </tr>
  <tr>
    <td rowspan="3">장바구니</td>
  </tr>
  <tr>
    <td>CRUD 구현 </td>
  </tr>
  <tr>
    <td>장바구니 페이지 구현, 결제시 해당 장바구니 제거 구현</td>
  </tr>
  <tr>
    <td rowspan="3">재고</td>
  </tr>
  <tr>
    <td>CRUD 기능 구현</td>
  </tr>
  <tr>
    <td>주문 결제 시 갯수 차감 구현 및 더미데이터 생성 </td>
  </tr>
  <tr><td rowspan="3">상품</td></tr>
  <tr><td>CRUD 구현</td></tr>
  <tr><td>BeautifulSoup, Selenium 을 이용한 유니클로 상품 크롤링 -><br>상품데이터 수집 후 상품 자동등록 시스템 구현</td></tr>
  <tr><td>챗봇</td><td>Naver CLOVA ChatBot API 연결로 비동기식 데이터 통신 구현</td></tr>
  <tr><td>날씨</td><td>공공데이터 포털을 이용하여, 온도에 맞는 상품추천 구현</td></tr>
  <tr><td>기타</td><td>우편 번호 API를 이용한 회원등록 및 상품 결제시 제공데이터 구현</td></tr>
  

</table>


# 👀 테스트 과정 
노션 주소 :


# 참고자료
Intellij Mybatis -  https://kyun2.tistory.com/69 <br>
Selenium - https://wikidocs.net/177133 <br>


# 트러블 슈팅 

트러블 슈팅
- 기획단계 결정 지연
- 프로젝트 기획단계에서 매우 어려움을 줌
- ERD 작업간에 긴 시간에 소요됨
- 이후 프로젝트 참여 없이 딴짓거리함 (게임, 동영상)

- NCP 테스트 오류
- 무한로딩 이미지 mapper 오류 발생
- 해결 : 롤백 주소로 변환 후 오류 해결


- 로그인 api 문제
- 네이버의 api 테스트계정을 만들어야했기 때문에,
- 네이버 테스트 계정을 만들수 있덤 김지영 팀장만 테스트 가능하였음
- 테스트 계정 등록 및 심사 이후 진행 가능함

- 카테고리 분류 및 알고리즘 정리의 에로사항
- 3단계 카테고리 사이즈 컬러 를 기준으로 재고 관리를 하는것이 어려움
- 카테고리는 항상 불러와쟈야 되는데 메인에서 불러오면
- 딱 처음 들어갔을때만 뜨고 그 다음엔 안 뜬다
- 그래서 컨트롤러 독립 관리를 통한(DataController) 해당 문제

- pom 환경설정 오류

- 웹소켓 에러