package com.example.spring_jsp.book.keyword;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.product.ProductDTO;
import com.example.spring_jsp.shop.product.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
public class KeywordController {

    private final ProductServiceImpl productServiceImpl;

//    @GetMapping("")
//    public ModelAndView keywordAnalysis(HttpSession session,
//                                        @RequestParam(value = "bookid", defaultValue = "0") long bookid,
//                                        @RequestParam(value = "productid", defaultValue = "") String productid) {
//
//        ModelAndView result = new ModelAndView();
//
//        //param check
//        if (bookid == 0 || productid.isEmpty()) {
//
//            //세션 체크
//            if (session.getAttribute("sid") != null) {
//                String sid = (String) session.getAttribute("sid");
//
//                //구매여부 체크
//                if (!productServiceImpl.selectProductCountByBookUserProduct(bookid, productid, sid).isEmpty()) {
//                    //이미 구매한 경우
//                    return keywordServiceImpl.getKeywordAnalysis();
//                } else {
//                    notificationService.send(sid, "구매하지 않은 상품입니다.");
//                }
//            }
//        }
//        return result;
//    }

    @PostMapping(value ={"", "/"})
    public void purchaseKeywordAnalysis(HttpSession session, ProductDTO productDTO) {
        //세션 체크
        if (session.getAttribute("sid") != null) {
            String sid = (String) session.getAttribute("sid");

            //세션 아이디와 폼에서 받은 아이디가 일치하는지 확인
            if (sid.equals(productDTO.getUserid())) {
                //제품 구매
                int result = productServiceImpl.purchaseProduct(sid, productDTO);
            }
        }
    }
}