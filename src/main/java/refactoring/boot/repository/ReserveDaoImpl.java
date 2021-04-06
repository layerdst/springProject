package refactoring.boot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import refactoring.boot.dto.*;

import static refactoring.boot.repository.ReserveDaoSql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReserveDaoImpl implements ReserveDao{

    private final NamedParameterJdbcTemplate jdbc;
    private final PlatformTransactionManager transactionManager;

    private RowMapper<DisplayInfoDto> displayInfoMapper= BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);
    private RowMapper<ProductPricesDto> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPricesDto.class);
    private RowMapper<ProductImageDto> imageMapper =  BeanPropertyRowMapper.newInstance(ProductImageDto.class);
    private RowMapper<DisplayInfoImageDto> displayImgMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);
    private RowMapper<ResponseReservationDto> getReservMapper = BeanPropertyRowMapper.newInstance(ResponseReservationDto.class);

    private RowMapper<PriceDto> priceMapper = BeanPropertyRowMapper.newInstance(PriceDto.class);
    private RowMapper<ReservationDto> reservMapper = BeanPropertyRowMapper.newInstance(ReservationDto.class);
    private RowMapper<CancelResponseDto> cancelMapper = BeanPropertyRowMapper.newInstance(CancelResponseDto.class);




    public DisplayInfoDto getDisplayInfo(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);

        return jdbc.queryForObject(SELECT_DISPAYINFO, params, displayInfoMapper);
    }

    public List<ProductPricesDto> getListPrice(int id){
        Map<String, Integer> params = Collections.singletonMap("id", id);

        try {
            return jdbc.query(SELECT_PRICE_LIST, params, productPriceMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public ProductImageDto getMainProductImg(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject(SELECT_PRODUCT_IMGS_MA, params,imageMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public DisplayInfoImageDto getDisplayInfoImg(int id) {
        Map<String,Integer> params=Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject(SELECT_DISPLAY_IMG,params,displayImgMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int insertReservation(RequestReservationDto reservDto){
        int result = 0;

        int value = jdbc.queryForObject("select max(id) from reservation_info", Collections.emptyMap(), Integer.class);
        int maxValue = value+1;

        Map<String, String> params = new HashMap<>();

        params.put("id", ""+maxValue);
        params.put("product_id", ""+reservDto.getProductId());
        params.put("display_info_id", ""+reservDto.getDisplayInfoId());
        params.put("reservation_name", reservDto.getName());
        params.put("reservation_tel", reservDto.getTel());
        params.put("reservation_email", reservDto.getEmail());

        List<PriceDto> pricesList =reservDto.getPrices();

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = this.transactionManager.getTransaction(def);

        try {
            jdbc.update(INSERT_RESERV_INFO, params);

            for(int i=0;i<pricesList.size();i++) {
                Map<String, Integer> temp = new HashMap<>();
                temp.put("reservation_info_id",maxValue);
                temp.put("product_price_id",pricesList.get(i).getProductPriceId());
                temp.put("count",pricesList.get(i).getCount());
                jdbc.update(INSERT_RESERV_PRICES, temp);
            }

            this.transactionManager.commit(status);
            result = maxValue;


        }catch(DataAccessException e) {
            result = 0;
            this.transactionManager.rollback(status);
            e.printStackTrace();

        }catch(Exception e) {
            result= 0;
            e.printStackTrace();
        }

        return result;
    }

    public ResponseReservationDto getInsertReservInfo(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);

        try {
            return jdbc.queryForObject(SELECT_RESERV,params,getReservMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<PriceDto> getInsertReservPrice(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);

        return jdbc.query(SELECT_PRICES, params, priceMapper);
    }

    public List<ReservationDto> getReservations(String email) {
        Map<String, String> params = Collections.singletonMap("email", email);

        return jdbc.query(SELECT_RESERV_VIEW,params,reservMapper);
    }

    public int cancelReserv(int id, String email) {

        Map<String, Object> params = new HashMap<>();
        params.put("id" , id);
        params.put("email", email);

        int check = jdbc.queryForObject(CHECK_CANCELFLAG, params, Integer.class);

        if(check==1) {
            return 0;
        }
        return jdbc.update(CANCEL_RESERVATION, params);
    }

    public CancelResponseDto cancelResult(int id){

        Map<String, Integer> params = Collections.singletonMap("id", id);

        try{
            return jdbc.queryForObject(SELELCT_CANCEL_RESERVATION_INFO, params, cancelMapper);
        }catch(EmptyResultDataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<PriceDto> cancelPriceResult(int id){

        Map<String, Integer> params = Collections.singletonMap("id", id);

        return jdbc.query(SELECT_CANCEL_PRICES, params, priceMapper);

    }
}
