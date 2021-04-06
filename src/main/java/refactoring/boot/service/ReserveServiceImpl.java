package refactoring.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import refactoring.boot.dto.*;
import refactoring.boot.repository.ReserveDao;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static refactoring.boot.status.StatusCode.*;


@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService{

    private final ReserveDao reservDao;


    @Override
    public ResponseReservationDto insertReservation(RequestReservationDto reservationDto) {

        int insertResult = reservDao.insertReservation(reservationDto);
        ResponseReservationDto getReservDto =  updateGetReserv(insertResult);
        return getReservDto;
    }

    @Override
    public Map<String, List<ReservationDto>> getReservation(String email) {
        Map<String, List<ReservationDto>> tempMap = new HashMap<>();

        List<ReservationDto> entireReservations = reservDao.getReservations(email);

        List<ReservationDto> confirmReserveList = new ArrayList<>();
        List<ReservationDto> cancelReserveList = new ArrayList<>();
        List<ReservationDto> endReserveList = new ArrayList<>();

        for(int i=0; i<entireReservations.size();i++){
            DisplayInfoDto displayInfoTemp = reservDao.getDisplayInfo(entireReservations.get(i).getDisplayInfoId());
            entireReservations.get(i).setDisplayInfo(displayInfoTemp);

            if(entireReservations.get(i).isCancelYn()==true){
                cancelReserveList.add(entireReservations.get(i));
                continue;
            }

            if(getEndDate(entireReservations.get(i).getReservationDate())){
                endReserveList.add(entireReservations.get(i));
                continue;
            }

            confirmReserveList.add(entireReservations.get(i));
        }

        tempMap.put("conformList", confirmReserveList);
        tempMap.put("cancelList", cancelReserveList);
        tempMap.put("endList", endReserveList);

        return tempMap;
    }

    @Override
    public ResponseReservationDto updateGetReserv(int id) {
        ResponseReservationDto getReserveDto = reservDao.getInsertReservInfo(id);
        List<PriceDto> getReservePrice = reservDao.getInsertReservPrice(id);
        getReserveDto.setPrices(getReservePrice);

        return getReserveDto;
    }

    @Override
    public CancelResponseDto cancelReservation(int id, String email) {

        if(checkInt(id)){
            System.out.println("id");
            return null;
        }

        if(checkString(email)){
            System.out.println("email");
            return null;
        }

        if(emailCheck(email) != 200){
            System.out.println("email_reg");
            return null;
        }

        int i = reservDao.cancelReserv(id, email);

        if(i==0){
            System.out.println("0");
            return null;
        }else{
            System.out.println("========aa");
            CancelResponseDto cancelDto = reservDao.cancelResult(id);
            List<PriceDto> priceDtos = reservDao.cancelPriceResult(id);
            cancelDto.setPrices(priceDtos);

            return cancelDto;
        }

    }

    @Override
    public int checkDto(RequestReservationDto dto) {
        int status;

        if(checkString(dto.getName())) {
            status = NAME_NULL;
        }else if(checkString(dto.getTel())) {
            status = TEL_NULL;
        }else if(checkString(dto.getEmail())) {
            status = EMAIL_NULL;
        }else if(checkInt(dto.getCheckCount())) {
            status = COUNT_NULL;
        }else if(checkInt(dto.getDisplayInfoId())) {
            status = DISPLAYINFO_ID_NULL;
        }else if(checkInt(dto.getProductId())) {
            status = PRODUCT_ID_NULL;
        }else if(dto.getPrices().size()==0) {
            status = PRICES_NULL;
        }else if(dto.getPrices().get(0).getCount()==0) {
            status = PRICES_ZERO;
        }else {
            status = 200;
        }
        return status;
    }

    @Override
    public int emailCheck(String value) {
        int status = EMAIL_TYPE_ERR;

        if(checkString(value)) {
            return status;
        }

        String pattern = "^[0-9a-zA-Z]+([-_]?)([0-9a-zA-Z]+)*@([0-9a-zA-Z]+)*.([a-zA-Z]+)(.[a-zA-Z]+)?$";
        if(value.matches(pattern)) {
            status = 200;
        }
        return status;
    }

    @Override
    public int phoneCheck(String value) {
        int status = PHONE_TYPE_ERR;

        if(checkString(value)) {
            return status;
        }

        String pattern = "^01(?:0|1|[6-9])[-](\\d{3}|\\d{4})[-](\\d{4})$";
        if(value.matches(pattern)) {
            status = 200;
        }
        return status;
    }



    @Override
    public boolean checkString(String str) {
        return str==null || str.equals("");
    }

    boolean checkInt(int num) {
        return num<=0;
    }


    public boolean getEndDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String currentTemp = dateFormat.format(new Date());
        String reservTemp =  dateFormat.format(date);

        Date currentTime=null;
        Date reservTime=null;

        try {
            currentTime = dateFormat.parse(currentTemp);
            reservTime = dateFormat.parse(reservTemp);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        int compare = currentTime.compareTo(reservTime);

        if(compare > 0) {
            System.out.println("current 큼");
            return true;
        }

        return false;
    }
}
