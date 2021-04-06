package refactoring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /*
    메인 페이지 이동
    - RestController - MainController
     */
    @GetMapping("/")
    public String mainPage(){
        return "mainpage.html";
    }

     /*
    상품 상세 정보 페이지 이동
     */

    @GetMapping("/detail/{display_id}")
    public String detailPage(){
        return "detail.html";
    }

    /*
    예약하기 페이지 이동
    */
    @GetMapping("/reserv")
    public String reservPage(){

        return "reserve.html";
    }

    /*
    나의 예약 정보 페이지
     */

    @GetMapping("/myreserv")
    public String myReservPage(){
        return "myrservation.html";
    }
    
    
    /*
    리뷰 목록 페이지
    */
    @GetMapping("/review")
    public String reviewListPage(){
        return "review.html";
    }


    /*
    리뷰 작성 페이지
    */
    @GetMapping("/reviewWrite")
    public String reviewWritePage(){
        return "reviewWrite.html";
    }

    /*
    이메일 로그인 페이지
     */
    @GetMapping
    public String login(){
        return "bookinglogin.html";
    }
}
