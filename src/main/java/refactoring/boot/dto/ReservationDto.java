package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationDto {

    private int productId;
    private Date reservationDate;
    private String reservationEmail;
    private int reservationInfoId;
    private String reservationName;
    private String reservationTelephone;
    private boolean cancelYn;
    private Date createDate;
    private int displayInfoId;
    private Date modifyDate;
    private DisplayInfoDto displayInfo;
    private int totalPrice;
    private int discountTotalPrice;

}
