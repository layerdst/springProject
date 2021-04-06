package refactoring.boot.service;


import refactoring.boot.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductService {
    
    /*메인페이지*/
    public Map<String, List> getMainData();
    public List<DisplayDto> getDisplayList(int categoryId, int start);

    /*상세페이지 */
    public List<CommentDto> getListCommentAndImg(int id);
    public List<ProductImageDto> getProductImg(int id);

}
