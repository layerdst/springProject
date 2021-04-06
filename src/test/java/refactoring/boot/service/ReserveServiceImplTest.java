package refactoring.boot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import refactoring.boot.dto.CancelResponseDto;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReserveServiceImplTest {

    @Autowired
    private ReserveService reserveService;

    @Test
    public void test(){

        CancelResponseDto cancelResponseDto = reserveService.cancelReservation(2, "carami@connect.co.kr");
        System.out.println(cancelResponseDto.toString());
    }
}