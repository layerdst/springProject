package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CommentImagesDto {
    private String contentType;
    private Date createDate;
    private String deleteFlag;
    private int fileId;
    private String fileName;
    private int imageId;
    private Date modifyDate;
    private int reservationInfoId;
    private int reservationUserCommentId;
    private String saveFileName;
}
