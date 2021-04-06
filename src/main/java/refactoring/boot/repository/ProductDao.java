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
public interface ProductDao {


    /* 메인 페이지 CategoryList*/
    public List<CategoryDto> getCategoryList();

    /* 메인 페이지 카테고리별 DisplayList */
    public List<DisplayDto> getMainDisplayList(int categoryId, int start);

    /* 메인 페이지 전체 DisplayList*/
    public List<DisplayDto> getEntireDisplayList(int start);

    /* 메인 페이지 전체 PromotionList*/
    public List<PromotionDto> getMainPromotionList();

    /* 상세페이지 상단 캐러셀 [메인 ma] 이미지*/
    /* 상세페이지 상단 캐러셀 [기타 et] 이미지*/
    public ProductImageDto getMainProductImg(int id);
    public ProductImageDto getEtProductImg(int id);
    public List<ProductPricesDto> getListProductPrice(int id);
    public List<CommentDto> getListComment(int id);
    public List<CommentImagesDto> getListCommentImg(int id);
    public float getAvg(int id);
    public DisplayInfoDto getDisplayInfo(int id);
    public DisplayInfoImageDto getDisplayInfoImg(int id);
}
