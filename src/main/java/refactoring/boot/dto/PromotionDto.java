package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PromotionDto {
    private int id;
    private int productId;
    private String productImageUrl;
    private String type;
    private String productDescription;
    private String productContent;
    private String placeName;

}
