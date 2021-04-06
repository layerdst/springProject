package refactoring.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import refactoring.boot.dto.*;
import refactoring.boot.repository.ReserveDao;
import refactoring.boot.service.ReserveService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReserveApiController {

    private final ReserveService reserveService;
    private final ReserveDao reserveDao;

    @GetMapping("/reservations/{reservationEmail}")
    public  Map<String, List<ReservationDto>> reservationInfo(@PathVariable("reservationEmail") String email){

        if (reserveService.emailCheck(email)!=200) {
            return null;
        }
        return reserveService.getReservation(email);
    }

    @PostMapping("/reservations/")
    public ModelMap doReservation(@RequestBody RequestReservationDto dto, ModelMap map){

        ResponseReservationDto getDto;
        int status;

        status = reserveService.checkDto(dto);

        if(status==200) {
            status =reserveService.emailCheck(dto.getEmail());
        }

        if(status==200) {
            status= reserveService.phoneCheck(dto.getTel());
        }

        map.addAttribute("status", status);

        if(status!=200) {
            return map;

        }else {
            getDto = reserveService.insertReservation(dto);
            map.addAttribute("result", getDto);
        }

        return map;
    }

    @PatchMapping ("/reservations")
    public CancelResponseDto cancelReservation(@RequestParam("reservationId") int reservationId,
                                    @RequestParam("email") String email){

        return reserveService.cancelReservation(reservationId, email);
    }




}
