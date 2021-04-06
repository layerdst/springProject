package refactoring.boot.service;

import refactoring.boot.dto.*;

import java.util.List;
import java.util.Map;

public interface ReserveService {

    ResponseReservationDto insertReservation(RequestReservationDto reservationDto);
    Map<String, List<ReservationDto>> getReservation(String email);
    ResponseReservationDto updateGetReserv(int id);
    CancelResponseDto cancelReservation(int id, String email);



    //유효성체크
    int emailCheck(String value);
    int checkDto(RequestReservationDto dto);
    int phoneCheck(String value);
    boolean checkString(String str);

}
