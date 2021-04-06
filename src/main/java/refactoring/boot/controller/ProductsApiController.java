package refactoring.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import refactoring.boot.dto.*;
import refactoring.boot.repository.ProductDao;
import refactoring.boot.service.ProductService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductsApiController {

    private final ProductDao productDao;
    private final ProductService productService;

    @GetMapping("/products")
    public List<DisplayDto>  mainDisplayList(@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,
                                  @RequestParam(value = "start", defaultValue = "0") int start){

        List<DisplayDto> displayList = productService.getDisplayList(categoryId, start);

        return displayList;
    }

    @GetMapping("/products/{displayInfo}")
    public ModelMap getDisplayInfo(@PathVariable("displayInfo") int id){

        ModelMap model = new ModelMap();

        List<ProductImageDto> productImg = productService.getProductImg(id);
        List<CommentDto> commentList = productService.getListCommentAndImg(id);

        List<ProductPricesDto> productPriceList = productDao.getListProductPrice(id);
        float avg = productDao.getAvg(id);
        DisplayInfoDto displayInfo = productDao.getDisplayInfo(id);
        DisplayInfoImageDto displayInfoImg = productDao.getDisplayInfoImg(id);

        model.addAttribute("productImg", productImg);
        model.addAttribute("comments", commentList);
        model.addAttribute("productPrices", productPriceList);
        model.addAttribute("averageScore", avg);
        model.addAttribute("displayInfoImg", displayInfoImg);
        model.addAttribute("displayInfo", displayInfo);
        return model;
    }
}


