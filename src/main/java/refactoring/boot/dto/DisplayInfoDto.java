package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class DisplayInfoDto {
    private int categoryId;
    private String categoryName;
    private Date createDate;
    private int displayInfoId;
    private String email;
    private String homepage;
    private Date modifyDate;
    private String openingHours;
    private String placeLot;
    private String placeName;
    private String placeStreet;
    private String productContent;
    private String productDescription;
    private String productEvent;
    private int productId;
    private String telephone;
}
