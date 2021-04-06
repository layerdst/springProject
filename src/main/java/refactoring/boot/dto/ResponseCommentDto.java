package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class ResponseCommentDto {
    private String comment;
    private int commentId;
    private Date createDate;
    private Date modifyDate;
    private int productId;
    private int reservationInfoId;
    private int score;
    private CommentImagesDto commentImage;
}
