package refactoring.boot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import refactoring.boot.dto.*;

import java.util.*;

import static refactoring.boot.repository.ProductDaoSql.*;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao  {

    private final NamedParameterJdbcTemplate jdbc;

    /* 메인 페이지 RowMapper*/
    private RowMapper<CategoryDto> categoryRowMapper = BeanPropertyRowMapper.newInstance(CategoryDto.class);
    private RowMapper<DisplayDto> displayDtoRowMapper = BeanPropertyRowMapper.newInstance(DisplayDto.class);
    private RowMapper<PromotionDto> promotionDtoRowMapper = BeanPropertyRowMapper.newInstance(PromotionDto.class);

    /*
    상세페이지 RowMapper
    */
    private RowMapper<ProductImageDto> productImgMapper = BeanPropertyRowMapper.newInstance(ProductImageDto.class);
    private RowMapper<ProductPricesDto> productPriceMapper = BeanPropertyRowMapper.newInstance(ProductPricesDto.class);
    private RowMapper<CommentDto> commentMapper=BeanPropertyRowMapper.newInstance(CommentDto.class);
    private RowMapper<CommentImagesDto> commentImgMapper=BeanPropertyRowMapper.newInstance(CommentImagesDto.class);
    private RowMapper<DisplayInfoDto> displayInfoMapper=BeanPropertyRowMapper.newInstance(DisplayInfoDto.class);
    private RowMapper<DisplayInfoImageDto> displayInfoImgMapper=BeanPropertyRowMapper.newInstance(DisplayInfoImageDto.class);



    /* 메인 페이지 CategoryList*/
    public List<CategoryDto> getCategoryList(){
        return jdbc.query(SELECT_CATEGORY_LIST, categoryRowMapper);
    }

    /* 메인 페이지 카테고리별 DisplayList */
    public List<DisplayDto> getMainDisplayList(int categoryId, int start){
        Map<String, Integer> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("start", start);
        return jdbc.query(SELECT_DISPLAY_LIST, params,displayDtoRowMapper);
    }

    /* 메인 페이지 전체 DisplayList*/
    public List<DisplayDto> getEntireDisplayList(int start){
        HashMap<String, Object> params = new HashMap<>();
        params.put("start", start);
        return jdbc.query(SELECT_ENTIRE_DISPLAY_LIST, params, displayDtoRowMapper);
    }

    /* 메인 페이지 전체 PromotionList*/
    public List<PromotionDto> getMainPromotionList(){
        return jdbc.query(SELECT_PROMOTION_LIST, promotionDtoRowMapper);
    }


    /*상세페이지 상단 메인[type=MA] 이미지 */
    public ProductImageDto getMainProductImg(int id){
        Map<String , ?> params = Collections.singletonMap("id",id);
        try {
            return jdbc.queryForObject(SELECT_PRODUCT_IMGS_MA_LIST,params,productImgMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*상세페이지 상단 상세[type=ET] 이미지 */
    public ProductImageDto getEtProductImg(int id){
        Map<String , ?> params = Collections.singletonMap("id",id);
        try {
            return jdbc.queryForObject(SELECT_PRODUCT_IMGS_ET_LIST,params,productImgMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    /*상세페이지 가격리스트 */
    public List<ProductPricesDto> getListProductPrice(int id){
        Map<String , ?> params = Collections.singletonMap("id",id);
        return jdbc.query(SELECT_PRODUCT_PRICE_LIST,params,productPriceMapper);
    }

    /*상세페이지 Comment 리스트 */
    public List<CommentDto> getListComment(int id){
        Map<String, ?> params = Collections.singletonMap("id",id);
        return jdbc.query(SELECT_COMMENT_LIST, params, commentMapper);
    }
    
    /*상세페이지 Comment Img 리스트 */
    public List<CommentImagesDto> getListCommentImg(int id){
        Map<String, ?> params = Collections.singletonMap("id",id);
        return jdbc.query(SELECT_COMMENT_IMG_LIST, params,commentImgMapper);
    }
    
    /*상세페이지 공연 평점*/
    public float getAvg(int id) {
        Map<String, ?> params = Collections.singletonMap("id",id);
        try {
            return jdbc.queryForObject(AVG_SCORE, params, float.class);
        }catch(EmptyResultDataAccessException e) {
            return 0;
        }
    }
    
    /*상세페이지 공연정보*/
    public DisplayInfoDto getDisplayInfo(int id) {
        Map<String, ?> params = Collections.singletonMap("id",id);
        try {
            return jdbc.queryForObject(SELECT_DISPAYINFO, params,displayInfoMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    /*상세페이지 공연 위치 이미지*/
    public DisplayInfoImageDto getDisplayInfoImg(int id) {
        Map<String,?> params = Collections.singletonMap("id", id);
        try {
            return jdbc.queryForObject(SELECT_DISPLAYINFO_IMG, params,displayInfoImgMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }

    }
}
