package refactoring.boot.repository;

import refactoring.boot.dto.*;

import java.util.List;

public interface ReserveDao {

    DisplayInfoDto getDisplayInfo(int id);
    List<ProductPricesDto> getListPrice(int id);
    ProductImageDto getMainProductImg(int id);
    DisplayInfoImageDto getDisplayInfoImg(int id);
    int insertReservation(RequestReservationDto reservDto);
    ResponseReservationDto getInsertReservInfo(int id);
    List<PriceDto> getInsertReservPrice(int id);
    List<ReservationDto> getReservations(String email);
    int cancelReserv(int id, String email);
    CancelResponseDto cancelResult(int id);
    List<PriceDto> cancelPriceResult(int id);

}
