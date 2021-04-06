package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RequestReservationDto {

    private String name;
    private String tel;
    private String email;
    private int displayInfoId;
    private int productId;
    private int checkCount;
    private Date date;
    private List<PriceDto> prices;

}
