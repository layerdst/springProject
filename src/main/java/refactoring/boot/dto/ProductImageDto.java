package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class ProductImageDto {
    private String contentType;
    private Date createDate;
    private String deleteFlag;
    private int fileInfoId;
    private String fileName;
    private Date modifyDate;
    private int productId;
    private int productImageId;
    private String saveFileName;
    private String type;
}
