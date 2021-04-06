package refactoring.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class CancelResponseDto {
    private int reservationInfoId;
    private int productId;
    private int displayInfoId;
    private String reservationName;
    private String reservationTelephone;
    private String reservationEmail;
    private Date reservationDate;
    private int cancelYn;
    private Date createDate;
    private Date modifyDate;
    private List<PriceDto> prices;

}
