package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class DisplayDto {
    private int categoryId;
    private int displayInfoId;
    private String productId;
    private String productDescription;
    private String placeName;
    private String productContent;
    private String productImageUrl;
}
