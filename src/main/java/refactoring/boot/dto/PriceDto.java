package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PriceDto {

    private int count;
    private int productPriceId;
    private int reservationInfoId;
    private int reservationInfoPriceId;
}
