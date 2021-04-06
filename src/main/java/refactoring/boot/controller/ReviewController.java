package refactoring.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import refactoring.boot.dto.ResponseCommentDto;
import refactoring.boot.service.ReservationReviewService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReservationReviewService reviewService;

    @PostMapping("/api/reservations/{reservationInfoId}/comments")
    public Map<String, Object> insertComment(@RequestParam(name="file", required=false) MultipartFile file,
                                             @RequestParam(name="reservationId", defaultValue="0") int resrvId,
                                             @RequestParam(name="productId", defaultValue="0") int productId,
                                             @RequestParam("comment") String comment,
                                             @RequestParam(name="score", defaultValue="0") int score){


        Map<String,Object> map = new HashMap<>();
        ResponseCommentDto commentDto =null;

        if(resrvId==0) {
            map.put("status", "203");
            return map;
        }else if(productId==0) {
            map.put("status", "204");
            return map;
        }else if(score==0) {
            map.put("status", "205");
            return map;
        }else if(comment==null || comment.equals("")) {
            map.put("status", "206");
            return map;
        }else {
            if(file!=null) {
                commentDto = reviewService.insertComment(resrvId,productId,score,comment,file);
                map.put("status", "200");

            }else {
                commentDto = reviewService.insertComment(resrvId,productId,score,comment,null);
                map.put("status", "201");

            }
        }

        map.put("comments",commentDto);
        return map;
    }
}
