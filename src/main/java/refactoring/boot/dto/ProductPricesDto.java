package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter
@ToString
public class ProductPricesDto {

    private Date createDate;
    private String discountRate;
    private Date modifyDate;
    private int price;
    private String priceTypeName;
    private int productId;
    private int productPriceId;
}
