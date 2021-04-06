package refactoring.boot.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponseReviewDto {

    int product_id;
    int reservation_id;
    String description;
}
