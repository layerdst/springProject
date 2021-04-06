package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class CommentDto {

    private String comment;
    private int commentId;
    private Date createDate;
    private Date modifyDate;
    private int productId;
    private int reservationInfoId;
    private int score;
    private Date reservationDate;
    private String reservationEmail;
    private String reservationName;
    private List<CommentImagesDto> commentImages;
}
