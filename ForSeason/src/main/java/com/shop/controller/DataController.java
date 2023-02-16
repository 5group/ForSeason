package com.shop.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.frame.ChatBotUtil;
import com.shop.service.*;
import org.json.simple.ItemList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shop.dto.Cart;
import com.shop.dto.Coupon;
import com.shop.dto.Item;
import com.shop.dto.Qna;
import com.shop.dto.Review;
import com.shop.dto.Size;
import com.shop.dto.Stock;
import com.shop.dto.User;
import com.shop.dto.WishList;
import com.shop.frame.CryptoUtil;
import com.shop.service.CartService;
import com.shop.service.ColorService;
import com.shop.service.ItemService;
import com.shop.service.QnaService;
import com.shop.service.ReviewService;
import com.shop.service.StockService;
import com.shop.service.UserService;
import com.shop.service.WishListService;

@RestController
public class DataController {

    @Value("${custdir}")
    String custdir;

    @Autowired
    HttpSession session;

    @Autowired
    StockService stockService;

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    ColorService colorService;

    @Autowired
    UserService userService;

    @Autowired
    WishListService wishListService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    QnaService qnaService;

    @Autowired
    MailService mailService;


    
    @RequestMapping(value = "/couponList", method = RequestMethod.GET)
    public List<Coupon> coupon_list() {
        List<Coupon> list = (List<Coupon>) session.getAttribute("coupon");
        if (list == null) {
            return null;
        }
        return list;
    }

    public List<HashMap<String, Object>> requiredOrderMap(List<Cart> cartList) throws Exception {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        for (Cart cart : cartList) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            int cart_cnt = cart.getCart_cnt();
            Stock stock = stockService.get(cart.getStock_no());
            int item_no = stock.getItem_no();
            Item item = itemService.get(item_no);
            map.put("cart_cnt", cart_cnt);
            map.put("item_name", item.getItem_name());
            map.put("item_price", item.getItem_price());
            map.put("item_discnt", item.getItem_discnt());
            map.put("stock", stock);
            list.add(map);
        }
        return list;
    }

    @RequestMapping(value = "/orderByCartList", method = RequestMethod.POST)
    public void orderByCartList(@RequestParam(value = "cartList[]") List<Integer> cartList, @RequestParam(value = "cartCntList[]") List<Integer> cartCntList) throws Exception {
        List<Cart> orderCartList = new ArrayList<Cart>();
        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = new Cart();
            cart.setCart_no(cartList.get(i));
            cart.setCart_cnt(cartCntList.get(i));
            cartService.modify(cart);
            orderCartList.add(cartService.get(cartList.get(i)));
        }
        session.setAttribute("orderCartList", orderCartList);
    }

    @RequestMapping(value = "/insertWish", method = RequestMethod.GET)
    public void insertWish(int item_no) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        WishList wishList = new WishList(0, u.getUser_no(), item_no);
        wishListService.register(wishList);
    }

    @RequestMapping(value = "/deleteWish", method = RequestMethod.GET)
    public void deleteWish(int item_no) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        WishList wishList = new WishList(0, u.getUser_no(), item_no);
        wishListService.deleteUserWish(wishList);
    }

    @RequestMapping("/cartInsert")
    public Object cartinsert(int item_no, int color_no, int size_no, int cart_cnt) throws Exception {
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("item_no", item_no);
        map.put("color_no", color_no);
        map.put("size_no", size_no);
        int stock_no = stockService.getStockNo(map);
        Cart cart = new Cart(0, stock_no, user.getUser_no(), cart_cnt, null);
        Cart checkCart = cartService.checkCartList(cart);
        if (checkCart != null) { //카트에 이미 담겨있으면 카트DB에 넣지 말고 return하기
            result = 1;
            return result;
        }
        cartService.register(cart);
        List<Cart> cart_list = cartService.get_list(user.getUser_no());
        session.setAttribute("cartList", cart_list); // test를 위한 sesstion 처리
        //(user.getUser_no());
        return result;
    }

    @RequestMapping("/getStock")
    public Object getStock(int item_no, int color_no, int color_index, @RequestParam(value = "CateArr[]") String[] CateArr) throws Exception {  //현재 선택한 컬러의 이미지파일 가져가면 되지 않나..?
        JSONObject jo = new JSONObject();
        List<Size> colorStock = stockService.getStock(item_no, color_no);   //현재 존재하는 재고만(사이즈) 리스트 불러오기 ex) 노랑색은 xs,s 분홍색은 xs만 있을 수 있으니깐
        Item item = itemService.get(item_no); //해당 아이템 이름
        String item_name = item.getItem_name();
        String src = CateArr[0] + "/" + CateArr[1] + "/" + CateArr[2] + "/" + item_name;
        File dir = new File(custdir + src);
        String imgnames[] = dir.list();
        jo.put("imgsrc", imgnames[color_index]);
        jo.put("colorStock", colorStock);
        return jo;
    }

    @RequestMapping("/checkUserPwd")
    public Object checkPwd(String user_pwd) throws Exception {
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        String pwd = user.getUser_pwd();
        if (pwd.equals(CryptoUtil.encryptAES256(user_pwd, "123456testsogood"))) {
            result = 1;
        }
        return result;
    }

    @RequestMapping(value = "/reviewInsert", method = RequestMethod.POST)
    public Object reviewInsert(int item_no, String rev_title, String rev_content, double rev_score) throws Exception {
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        Review review = new Review(user.getUser_no(), item_no, rev_title, rev_content, rev_score);
        reviewService.register(review);
        return result;
    }

    @RequestMapping(value = "/QnaInsert", method = RequestMethod.POST)
    public Object QnaInsert(String qna_title, String qna_content) throws Exception {
        //(qna_title);
        //(qna_content);
        int result = 0;
        User user = (User) session.getAttribute("loginUser");
        Qna qna = new Qna(user.getUser_no(), qna_title, qna_content);
        //(qna);

        qnaService.register(qna);
        return result;
    }

    @RequestMapping(value = "/updateReview", method = RequestMethod.POST)
    public void updateReview(Review review) throws Exception {
        review.setRev_no(review.getRev_no());
        review.setRev_title(review.getRev_title());
        review.setRev_content(review.getRev_content());
        review.setRev_score(review.getRev_score());
        review.setRev_rdate(new Date());
        reviewService.modify(review);
    }


    @RequestMapping("/find_id")
    public String findId(User user) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("user_phone", user.getUser_phone());
        map.put("user_name", user.getUser_name());
        map.put("user_email", user.getUser_email());
        if (null != userService.findUserId(map)) {
            user.setUser_id(userService.findUserId(map));
            mailService.getEmailByFindId(user);
            return "해당이메일로 안전하게 보내드렸습니다.";
        }
        return "not found";
    }

    @RequestMapping("/find_pwd")
    public String findPwd(User user) throws Exception {
        String id = user.getUser_id();
        String phone = user.getUser_phone();
        String email = user.getUser_email();
        User findUser = userService.get_id(id);
        if (findUser != null && findUser.getUser_phone().equals(phone) && findUser.getUser_email().equals(email)) {
            User resultUser = mailService.userAndEmailByPwdReset(findUser, email);
            user.setUser_pwd(resultUser.getUser_pwd());
            userService.set_pwd(user);
            return "해당이메일로 안전하게 보내드렸습니다.";
        }
        return "not found";
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public void infoUpdate(User info_user) throws Exception {
        User u = (User) session.getAttribute("loginUser");
        u.setUser_id(u.getUser_id());
        u.setUser_email(info_user.getUser_email());
        u.setUser_name(info_user.getUser_name());
        u.setUser_phone(info_user.getUser_phone());
        u.setUser_address(info_user.getUser_address());
        userService.modify(u);
        session.setAttribute("loginUser", u);
    }

	@RequestMapping("/getmarker2")
	public Object getmarker2(String loc) {
		JSONArray ja = new JSONArray();

			JSONObject jo1 = new JSONObject();
			jo1.put("title", "ForSeason 본사");
			//jo1.put("target", "http://www.naver.com");
			jo1.put("address", "서울특별시 강남구 도산대로 145 인우빌딩 B1~2F");
			jo1.put("info", "남성, 여성, 어린이를 위한 캐주얼웨어를 선보이는 의류소매점입니다.");
			jo1.put("lat", 37.518469);
			jo1.put("lng", 127.0244304);
			jo1.put("img", "mapImg.jpg");
			ja.add(jo1);


		return ja;
	}

    @RequestMapping("/randomItemList")
    public Object randomItemList() throws Exception {
        Random random = new Random();
        List<Item> result = new ArrayList<>();
        int weatherIndex = Integer.parseInt((String) session.getAttribute("weather"));
        for (int i = 0; i <= 2; i++) {
            if (10 > weatherIndex) {
                int cateNo = 10 + i * 30;
                List<Item> itemList = itemService.midCateByItemList(cateNo);
                result.add(itemList.get(random.nextInt(itemService.midCateByItemList(cateNo).size())));
                //(result);
            } else {
                result.add(itemService.get().get(random.nextInt(itemService.get().size())));
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/chatBot")
    public String chatBot(String messageContent) throws IOException {
        //(messageContent);
        if(null == messageContent) {
            return "넌잘못됐다";
        }else {
            String result = ChatBotUtil.chat(messageContent);
            return result;
        }
    }


    @GetMapping("/api/weather")
    public String restApiGetWeather() throws Exception {
        Date date = new Date();
        SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat f2 = new SimpleDateFormat("HH");
        String yyyyMMdd = f1.format(date);
        String hh = String.valueOf(Integer.parseInt(f2.format(date)) - 4)+"00";
        //(hh);
        String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                + "?serviceKey=jjjiDNS%2BzqFXxPo8JNr%2F%2Bk%2BCXU0wMKxiH%2BDHqy3wkSzoqaRuIfRCcsiYMFo%2BEQaenBccGaZPv65Lz6TyDx89DA%3D%3D"
                + "&dataType=JSON"            // JSON, XML
                + "&numOfRows=10"             // 페이지 ROWS
                + "&pageNo=1"                 // 페이지 번호
                + "&base_date=" + 20230216    // 발표일자
                + "&base_time=0800"        // 발표시각
                + "&nx=60"                    // 예보지점 X 좌표
                + "&ny=127";                  // 예보지점 Y 좌표

        HashMap<String, Object> resultMap = getDataFromJson(url, "UTF-8", "get", "");
        Map<String, Object> response = (Map<String, Object>) resultMap.get("response");
        Map<String, Object> body = (Map<String, Object>) response.get("body");
        Map<String, Object> items = (Map<String, Object>) body.get("items");
        List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
        String firstFcstValue = (String) itemList.get(0).get("fcstValue");
        session.setAttribute("weather", firstFcstValue);
        return firstFcstValue;
    }


    public HashMap<String, Object> getDataFromJson(String url, String encoding, String type, String jsonStr) throws Exception {
        boolean isPost = false;
        if ("post".equals(type)) {
            isPost = true;
        } else {
            url = "".equals(jsonStr) ? url : url + "?request=" + jsonStr;
        }
        return getStringFromURL(url, encoding, isPost, jsonStr, "application/json");
    }

    public HashMap<String, Object> getStringFromURL(String url, String encoding, boolean isPost, String parameter, String contentType) throws Exception {
        URL apiURL = new URL(url);
        HttpURLConnection conn = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        try {
            conn = (HttpURLConnection) apiURL.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);
            if (isPost) {
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", contentType);
                conn.setRequestProperty("Accept", "*/*");
            } else {
                conn.setRequestMethod("GET");
            }
            conn.connect();
            if (isPost) {
                bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                bw.write(parameter);
                bw.flush();
                bw = null;
            }
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = br.readLine()) != null) result.append(line);
            ObjectMapper mapper = new ObjectMapper();
            resultMap = mapper.readValue(result.toString(), HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(url + " interface failed" + e.toString());
        } finally {
            if (conn != null) conn.disconnect();
            if (br != null) br.close();
            if (bw != null) bw.close();
        }

        return resultMap;
    }
}

