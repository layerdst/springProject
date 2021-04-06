package refactoring.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import refactoring.boot.dto.*;
import refactoring.boot.repository.ProductDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    @Override
    public Map<String, List> getMainData() {
        Map<String, List> dataMap = new HashMap<>();
        List<CategoryDto> categoryList = productDao.getCategoryList();
        List<PromotionDto> promotionList = productDao.getMainPromotionList();
        List<DisplayDto> entireDisplayList = productDao.getEntireDisplayList(0);

        dataMap.put("categoryList", categoryList);
        dataMap.put("promotionList", promotionList);
        dataMap.put("entireDisplayList", entireDisplayList);

        return dataMap;
    }

    @Override
    public List<DisplayDto> getDisplayList(int categoryId, int start) {
        if(categoryId==0){
            return productDao.getEntireDisplayList(start);
        }
        return productDao.getMainDisplayList(categoryId,start);
    }

    @Override
    public List<ProductImageDto> getProductImg(int id){
        List<ProductImageDto> list = new ArrayList<>();

        ProductImageDto mainProductImg = productDao.getMainProductImg(id);
        ProductImageDto etProductImg = productDao.getEtProductImg(id);

        list.add(mainProductImg);
        if(etProductImg != null){
            list.add(etProductImg);
        }
        return list;
    }

    @Override
    public List<CommentDto> getListCommentAndImg(int id){

        List<CommentDto> commentList = productDao.getListComment(id);
        List<CommentImagesDto> commentImgList = productDao.getListCommentImg(id);

        for(int i = 0 ; i<commentList.size(); i++){
            List <CommentImagesDto> tempImgList = new ArrayList<>();
            for(int j = 0; j<commentImgList.size();j++){
                if(commentList.get(i).getCommentId() == commentImgList.get(j).getReservationUserCommentId()){
                    tempImgList.add(commentImgList.get(j));
                }
            }
            commentList.get(i).setCommentImages(tempImgList);
        }

        return commentList;
    }
}
