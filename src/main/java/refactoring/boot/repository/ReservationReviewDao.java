package refactoring.boot.repository;

import refactoring.boot.dto.CommentImagesDto;
import refactoring.boot.dto.ResponseCommentDto;
import refactoring.boot.dto.ResponseReviewDto;

import java.util.Map;

public interface ReservationReviewDao {

    ResponseReviewDto getCommentPossible(int id);
    int insertComment(Map<String,Object> commentArgs, Map<String,Object> fileArgs);
    ResponseCommentDto getInsertComment(int id);
    CommentImagesDto getInsertCommentImg(int id);


}
