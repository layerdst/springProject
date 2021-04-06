package refactoring.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import refactoring.boot.dto.CategoryDto;
import refactoring.boot.dto.PromotionDto;
import refactoring.boot.repository.ProductDao;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainApiController {

    private final ProductDao productDao;

    @GetMapping("/categories")
    public List<CategoryDto> getCategories(){
        List<CategoryDto> categoryList = productDao.getCategoryList();
        return categoryList;
    }

    @GetMapping("/promotions")
    public List<PromotionDto> getPromotions() {
        List<PromotionDto> mainPromotionList = productDao.getMainPromotionList();
        return mainPromotionList;
    }
}
