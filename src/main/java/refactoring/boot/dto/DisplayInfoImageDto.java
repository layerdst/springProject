package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DisplayInfoImageDto {
    private String contentType;
    private Date createDate;
    private String deleteFlag;
    private int displayInfoId;
    private int displayInfoImageId;
    private int fileId;
    private String fileName;
    private Date modifyDate;
    private String saveFileName;

}
