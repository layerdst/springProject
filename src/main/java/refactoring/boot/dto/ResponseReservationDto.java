package refactoring.boot.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ResponseReservationDto {

    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private Date modifyDate;
    private int reservationInfoId;
    private int displayInfoId;
    private int productId;
    private List<PriceDto> prices;

}
