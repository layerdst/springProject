package refactoring.boot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import refactoring.boot.dto.ResponseCommentDto;
import refactoring.boot.dto.ResponseReviewDto;


public interface ReservationReviewService {
    public ResponseReviewDto getCommentPossible(int id);
    public ResponseCommentDto insertComment(int resrvId, int productId, int score, String comment, MultipartFile file);

}
